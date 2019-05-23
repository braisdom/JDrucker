package org.braisdom.drucker;

import org.braisdom.drucker.xsql.XSQLDeclarations;
import org.braisdom.drucker.xsql.XSQLDeclarations.*;
import org.braisdom.drucker.xsql.XSQLSyntaxError;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XSQLDeclarationsTest {

    @Test
    public void testDialectDeclSucc() throws IOException {
        String input = "dialect: mysql;\n" +
                "initialize users {\n" +
                "} ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        XSQLDeclaration xsqlDeclaration = XSQLDeclarations.parse(inputStream);
        Assert.assertTrue("mysql".equals(xsqlDeclaration.getDialect()));
    }

    @Test
    public void testDialectDeclFail() {
        try {
            String input = "ialect: mysql;\n" +
                    "initialize users {\n" +
                    "} ";
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());
            XSQLDeclaration xsqlDeclaration = XSQLDeclarations.parse(inputStream);
            Assert.assertFalse("mysql".equals(xsqlDeclaration.getDialect()));
        } catch (Exception ex) {
            Assert.assertEquals(XSQLSyntaxError.class, ex.getClass());
        }
    }

    @Test
    public void testInitSql() throws IOException {
        String input = "dialect: mysql;\n " +
                "initialize users \n" +
                "{\n" +
                "  CREATE TABLE ( ); \n" +
                "} ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        XSQLDeclaration xsqlDeclaration = XSQLDeclarations.parse(inputStream);
        Assert.assertEquals(xsqlDeclaration.getInitializeDeclaration().getTableName(), "users");
        Assert.assertTrue(xsqlDeclaration.getInitializeDeclaration().getSqlStatements().size() > 0);
    }

    @Test
    public void testMigrations() throws IOException {
        String input = "dialect: mysql;\n " +
                "initialize users \n {} \n" +
                "migrations { \n" +
                " version 0.1 { \n" +
                "  ALTER TABLE t2 DROP COLUMN c, DROP COLUMN d;\n" +
                "  ALTER TABLE t2 DROP COLUMN c, DROP COLUMN d;\n" +
                " } \n" +
                "}";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        XSQLDeclaration xsqlDeclaration = XSQLDeclarations.parse(inputStream);
        Assert.assertTrue(xsqlDeclaration.getMigrations().getMigrations().size() > 0);
    }

    @Test
    public void testSql() throws IOException {
        String input = "dialect: mysql;\n " +
                "initialize users \n {} \n" +
                "migrations {} \n" +
                "mysql sql find_all_users {\n" +
                "  SELECT * FROM users;" +
                "}";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        XSQLDeclaration xsqlDeclaration = XSQLDeclarations.parse(inputStream);
        Assert.assertTrue(xsqlDeclaration.getSqlStatements().size() > 0);
    }
}
