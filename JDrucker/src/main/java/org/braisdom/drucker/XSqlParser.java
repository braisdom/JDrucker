package org.braisdom.drucker;

import org.apache.commons.io.FileUtils;
import org.jparsec.*;
import org.jparsec.internal.util.Strings;
import org.jparsec.pattern.Patterns;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author braisdom
 * @since 1.0.0
 */
public class XSqlParser {

    private static final String[] OPERATORS = {
            "+", "-", "*", "/", "%", ">", "<", "=", ">=", "<=", "<>", ".", ",", "(", ")", "[", "]", "$", ";", ":", ",", "{", "}", "${", "'"
    };

    private static final String[] SQL_OPERATORS = {
            "+", "-", "*", "/", "%", ">", "<", "=", ">=", "<=", "<>", ".", ",", "(", ")", "[", "]", "$", ";", ":", ",", "'"
    };

    private static final Terminals TERMS = Terminals
            .operators(OPERATORS)
            .words(Scanners.IDENTIFIER)
            .keywords("sql")
            .build();

    private static final Parser<Void> IGNORED = Parsers.or(
            Patterns.lineComment("#").toScanner("#"),
            Scanners.JAVA_LINE_COMMENT,
            Scanners.JAVA_BLOCK_COMMENT,
            Scanners.WHITESPACES).skipMany();

    private static final Parser<?> TOKENIZER = Parsers.or(
            Terminals.StringLiteral.DOUBLE_QUOTE_TOKENIZER,
            Terminals.CharLiteral.SINGLE_QUOTE_TOKENIZER,
            TERMS.tokenizer());

    private static final List<String> SQL_CHARACTERS = Arrays.asList(SQL_OPERATORS);

    private static final Parser<String> SQL_CHARACTER_PARSER = Parsers.token(new TokenMap<String>() {

        @Override public String map(final Token token) {
            final Object val = token.value();
            if (val instanceof Tokens.Fragment) {
                Tokens.Fragment c = (Tokens.Fragment) val;
                if(!Tokens.Tag.IDENTIFIER.toString().equals(c.tag().toString()) && !SQL_CHARACTERS.contains(c.text()))
                    return null;
                return c.text();
            }
            else return null;
        }

        @Override public String toString() {
            return "[SQL keywords and operators]";
        }
    });

    private static final Parser SQL_STATEMENT_PARSER = Parsers.or(
            SQL_CHARACTER_PARSER,
            Parsers.between(
                    term("${"),
                    Terminals.Identifier.PARSER.sepBy(term(".")),
                    term("}")).map(XSqlEvaluation::new)
    );

    private static final Parser<XSqlBlock> SQL_BLOCK_PARSER = Parsers.sequence(
            term("sql"),
            Terminals.Identifier.PARSER,
            Parsers.between(
                    term("{"),
                    SQL_STATEMENT_PARSER.endBy(term(";").asOptional()),
                    term("}")
            ), XSqlBlock::new);

    private static final Parser<XSql> XSQL_PARSER = Parsers.sequence(SQL_BLOCK_PARSER.asOptional().many()).map(XSql::new);

    private static class XSql {

        private final List<XSqlBlock> blocks;

        public XSql(Object blocks) {
            if (blocks instanceof List)
                this.blocks = (List<XSqlBlock>) blocks;
            else
                throw new IllegalStateException("Invalid blocks" + blocks);
        }

    }

    private static class XSqlBlock {

        private final Token keyword;
        private final String blockId;
        private final List sqlSegment;

        public XSqlBlock(Token keyword, String blockId, List sqlSegment) {
            this.keyword = keyword;
            this.blockId = blockId;
            this.sqlSegment = sqlSegment;
        }
    }

    private static class XSqlEvaluation {
        private final List<String> expressions;

        public XSqlEvaluation(List<String> expressions) {
            this.expressions = expressions;
        }
    }

    public static final String parse(String fileName) throws IOException {
        String sqlContent = FileUtils.readFileToString(new File(XSqlParser.class.getResource(fileName).getFile()));
        XSql xSql = XSQL_PARSER.from(TOKENIZER, IGNORED).parse(sqlContent, Parser.Mode.DEBUG);
        return "";
    }

    private static Parser term(String tokenName) {
        return TERMS.token(tokenName);
    }

}
