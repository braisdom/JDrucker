package org.braisdom.drucker.xsql;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.jparsec.*;
import org.jparsec.pattern.Patterns;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author braisdom
 * @since 1.0.0
 */
public final class XSQLParser {

    private static final String SQL_LINE_DELIMITER = "\n";

    private static final Parser<String> IDENTIFIER = Patterns.isChar(Character::isJavaIdentifierStart)
            .next(Patterns.isChar(Character::isJavaIdentifierPart).many())
            .toScanner("identifier")
            .source();

    private static final Terminals TERMS = Terminals
            .operators(new String[]{})
            .words(IDENTIFIER)
            .keywords("sql", "dialect:", "mysql", "new table", "migrations", "version", "oracle", "sqlite", "postgresql")
            .build();

    private static final Parser<Void> IGNORED = Parsers.or(
            Patterns.lineComment("#").toScanner("#"),
            Scanners.JAVA_LINE_COMMENT,
            Scanners.JAVA_BLOCK_COMMENT,
            Scanners.WHITESPACES).skipMany();

    private static final Parser<?> TOKENIZER = Parsers.or(
            new SqlBlockScanner(),
            TERMS.tokenizer()
    );

    private static final Parser<?> SQL_DIALECT = Parsers.or(
            term("mysql"), term("oracle"), term("sqlite"), term("postgresql")
    );

    private static final Parser<?> DIALECT_BLOCK = Parsers.sequence(
            term("dialect:"), SQL_DIALECT
    );

    private static final Parser<XSqlBlock> SQL_BLOCK_PARSER = Parsers.sequence(
            term("sql"),
            Terminals.Identifier.PARSER,
            Parsers.token(token -> {
                String value = token.value().toString();
                if (value.charAt(0) == '{' && value.charAt(value.length() - 1) == '}') {
                    return value.substring(1, value.length() - 1).trim();
                } else return null;
            }).label("SQL statement"), XSqlBlock::new).label("SQL block");


    private static final Parser<XSqlFile> XSQL_PARSER = Parsers
            .or(DIALECT_BLOCK.optional(null), SQL_BLOCK_PARSER.many()).map(XSqlFile::new);

    private static class XSqlFile {

        private final List<XSqlBlock> blocks;
        private final Map<String, XSqlBlock> xSqlBlockMap;

        public XSqlFile(Object blocks) {
            if (blocks instanceof List) {
                this.blocks = (List<XSqlBlock>) blocks;
                xSqlBlockMap = new HashMap<>();
                for (XSqlBlock block : this.blocks) {
                    if (xSqlBlockMap.containsKey(block.sqlId))
                        throw new XSQLException("Duplicated sqlId: " + block.sqlId);
                    xSqlBlockMap.put(block.sqlId, block);
                }
            } else
                throw new XSQLException("Invalid blocks" + blocks);
        }

        public XSqlBlock getSqlBlock(String sqlId) {
            return xSqlBlockMap.get(sqlId);
        }
    }

    private static class XSqlBlock {
        private final String keyword;
        private final String sqlId;
        private final String sql;

        public XSqlBlock(Token keyword, String sqlId, String sql) {
            this.keyword = keyword.toString();
            this.sqlId = sqlId;
            this.sql = sql;
        }
    }

    private static Map<String, XSqlFile> xSqlFileMap = new HashMap<>();
    private static Configuration templateConfiguration = new Configuration();

    private XSQLParser() {
    }

    public static final String parse(String fileName,
                                     String sqlId,
                                     Class resourceClass,
                                     Map<String, Object> dataModel) throws XSQLParsingException {
        Objects.requireNonNull(fileName, "xsql file cannot be null");
        Objects.requireNonNull(sqlId, "sqlId cannot be null");
        Objects.requireNonNull(dataModel, "dataModel cannot be null");

        if (fileName.length() == 0)
            throw new XSQLParsingException("xsql file is blank");
        if (sqlId.length() == 0)
            throw new XSQLParsingException("sqlId is blank");

        try {
            XSqlFile xSqlFile = xSqlFileMap.get(fileName);

            if (xSqlFile == null) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(resourceClass.getResourceAsStream(fileName)));
                String xSqlFileContent = buffer.lines().collect(Collectors.joining(SQL_LINE_DELIMITER));
                xSqlFile = XSQL_PARSER.from(TOKENIZER, IGNORED).parse(xSqlFileContent);
                xSqlFileMap.put(fileName, xSqlFile);
            }

            String sql = xSqlFile.getSqlBlock(sqlId).sql;
            StringWriter out = new StringWriter();

            Template template = new Template(sqlId, sql, templateConfiguration);
            template.process(dataModel, out);
            return out.toString();
        } catch (IOException ex) {
            throw new XSQLParsingException(ex.getMessage(), ex);
        } catch (TemplateException ex) {
            throw new XSQLParsingException(ex.getMessage(), ex);
        }
    }

    private static Parser term(String tokenName) {
        return TERMS.token(tokenName);
    }
}
