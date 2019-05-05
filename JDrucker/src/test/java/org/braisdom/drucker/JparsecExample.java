package org.braisdom.drucker;

import org.jparsec.*;
import org.jparsec.internal.util.Strings;
import org.jparsec.pattern.Patterns;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JparsecExample {

    private static final String[] OPERATORS = {
            "+", "-", "*", "/", "%", ">", "<", "=", ">=", "<=", "<>", ".", ",", "(", ")", "[", "]", "$", ";", ":", ",", "{", "}", "${", "'"
    };

    private static final String [] SQL_OPERATOR = {
            "+", "-", "*", "/", "%", ">", "<", "=", ">=", "<=", "<>", ".", ",", "(", ")", "[", "]", "$", ";", ":", ",", "'"
    };

    private static final Terminals TERMS = Terminals
            .operators(OPERATORS)
            .words(Scanners.IDENTIFIER)
            .keywords("sql")
            .build();

    static final Parser<Void> IGNORED = Parsers.or(
            Patterns.lineComment("#").toScanner("#"),
            Scanners.JAVA_LINE_COMMENT,
            Scanners.JAVA_BLOCK_COMMENT,
            Scanners.WHITESPACES).skipMany();

    static final Parser<?> TOKENIZER = Parsers.or(
            Terminals.StringLiteral.DOUBLE_QUOTE_TOKENIZER,
            Terminals.CharLiteral.SINGLE_QUOTE_TOKENIZER,
            TERMS.tokenizer());

    public static void main(String[] args) throws IOException {
        final String[] tags = new String[]{"SQL keywords and operators", Tokens.Tag.IDENTIFIER.toString()};
        List<String> operators = Arrays.asList(SQL_OPERATOR);
        Parser<String> sqlParser = Parsers.token(new TokenMap<String>() {
            @Override public String map(final Token token) {
                final Object val = token.value();
                if (val instanceof Tokens.Fragment) {
                    Tokens.Fragment c = (Tokens.Fragment) val;
                    if(!Tokens.Tag.IDENTIFIER.toString().equals(c.tag().toString()) && !operators.contains(c.text()))
                        return null;
                    return c.text();
                }
                else return null;
            }
            @Override public String toString() {
                return "[" + Strings.join(", ", tags) + "]";
            }
        });

        Parser sqlStatementParse = Parsers.or(
                sqlParser,
                Parsers.between(term("${"),
                        Terminals.Identifier.PARSER.sepBy(term(".")), term("}")).map(XSqlEvaluation::new)
        );

        Parser sqlBlockParser = Parsers.sequence(
                term("sql"),
                Terminals.Identifier.PARSER,
                Parsers.between(term("{"), sqlStatementParse
                        .endBy(term(";").asOptional()), term("}")),
                XSqlBlock::new);

        Parser xsqlParse = Parsers.sequence(sqlBlockParser.asOptional().many()).map(XSql::new);
    }

    private static class XSql {

        private final Object xsql;

        public XSql(Object xsql) {
            this.xsql = xsql;
        }
    }

    private static Parser term(String tokenName) {
        return TERMS.token(tokenName);
    }

    private static class XSqlEvaluation {
        private final Object expression;

        public XSqlEvaluation(Object expression) {
            this.expression = expression;
        }
    }

    public static class XSqlBlock {

        public XSqlBlock(Object value1, Object value2, Object value3) {
            System.out.println();
        }

    }
}
