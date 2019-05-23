package org.braisdom.drucker;

import antlr4.XSQLBaseListener;
import antlr4.XSQLLexer;
import antlr4.XSQLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;

public class XSqlParserTest {

    public static void main(String[] args) throws Exception {
        CharStream antlrInputStream = CharStreams.fromStream(new FileInputStream("./test.xsql"));
        XSQLLexer xsqlLexer = new XSQLLexer((antlrInputStream));
        CommonTokenStream commonStream = new CommonTokenStream(xsqlLexer);
        XSQLParser xsqlParser = new XSQLParser(commonStream);
        xsqlParser.xsqlDecl().enterRule(new XSQLBaseListener() {
            @Override
            public void enterXsqlDecl(XSQLParser.XsqlDeclContext ctx) {
                super.enterXsqlDecl(ctx);
            }

            @Override
            public void enterDialectDecl(XSQLParser.DialectDeclContext ctx) {
                super.enterDialectDecl(ctx);
            }

            @Override
            public void enterInitDecl(XSQLParser.InitDeclContext ctx) {
                super.enterInitDecl(ctx);
            }
        });
    }
}
