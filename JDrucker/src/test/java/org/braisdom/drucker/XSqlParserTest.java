package org.braisdom.drucker;

import org.braisdom.drucker.xsql.XSqlParser;

import java.io.IOException;

public class XSqlParserTest {

    public static void main(String[] args) throws IOException {
        XSqlParser.parse(XSqlParserTest.class.getResourceAsStream("/sql/Users.xsql"));
    }
}
