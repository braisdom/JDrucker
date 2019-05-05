package org.braisdom.drucker.xsql;

import org.jparsec.*;
import org.jparsec.pattern.Patterns;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author braisdom
 * @since 1.0.0
 */
public class XSqlParser {

    private static final Parser<String> IDENTIFIER = Patterns.isChar(Character::isJavaIdentifierStart)
            .next(Patterns.isChar(Character::isJavaIdentifierPart).many())
            .toScanner("identifier")
            .source();

    private static final Terminals TERMS = Terminals
            .operators(new String[]{})
            .words(IDENTIFIER)
            .keywords("sql")
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
            .sequence(SQL_BLOCK_PARSER.many()).map(XSqlFile::new);

    private static class XSqlFile {

        private final List<XSqlBlock> blocks;

        public XSqlFile(Object blocks) {
            if (blocks instanceof List)
                this.blocks = (List<XSqlBlock>) blocks;
            else
                throw new IllegalStateException("Invalid blocks" + blocks);
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

    public static final String parse(InputStream xSqlFileInputStream) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(xSqlFileInputStream));
        String xSqlFileContent = buffer.lines().collect(Collectors.joining("\n"));
        XSqlFile xSqlFile = XSQL_PARSER.from(TOKENIZER, IGNORED).parse(xSqlFileContent);
        return "";
    }

    private static Parser term(String tokenName) {
        return TERMS.token(tokenName);
    }
}
