package org.braisdom.drucker.xsql;

import org.apache.commons.text.StrSubstitutor;
import org.apache.commons.text.StringSubstitutor;
import org.braisdom.drucker.xsql.XSQLDefinition.XSQLDeclaration;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XSQLDefinitionTest {

    @Test
    public void testDialectDeclSucc() throws IOException {
        String input = "dialect: mysql;\n" +
                "initialize users {\n" +
                "} ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        XSQLDeclaration xsqlDeclaration = XSQLDefinition.parse(inputStream);
        Assert.assertTrue("mysql".equals(xsqlDeclaration.getDialect()));
    }

    @Test
    public void testDialectDeclFail() {
        try {
            String input = "ialect: mysql;\n" +
                    "initialize users {\n" +
                    "} ";
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());
            XSQLDeclaration xsqlDeclaration = XSQLDefinition.parse(inputStream);
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
        XSQLDeclaration xsqlDeclaration = XSQLDefinition.parse(inputStream);
        Assert.assertEquals(xsqlDeclaration.getInitialize().getTableName(), "users");
        Assert.assertTrue(xsqlDeclaration.getInitialize().getSqlStatements().size() == 1);
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
        XSQLDeclaration xsqlDeclaration = XSQLDefinition.parse(inputStream);
        Assert.assertTrue(xsqlDeclaration.getMigrations().getMigrations().size() == 1);
        Assert.assertTrue(xsqlDeclaration.getMigrations().getMigrations().get(0).getSqlStatements().size() == 2);
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
        XSQLDeclaration xsqlDeclaration = XSQLDefinition.parse(inputStream);
        Assert.assertTrue(xsqlDeclaration.getSqlStatements().size() == 1);
        Assert.assertTrue(xsqlDeclaration.getSqlStatements().get(0).getSqlStatements().size() == 1);
    }

    @Test
    public void testGetSqlStatement() throws IOException {
        String sqlId = "query_users";
        String sqlId2 = "query_users2";

        XSQLDeclaration xsqlDeclaration = XSQLDefinition.parse("xsql/users.xsql", XSQLDefinitionTest.class.getClassLoader());
        XSQLDefinition.Sql sql = xsqlDeclaration.getSqlStatement(sqlId);
        XSQLDefinition.Sql sql2 = xsqlDeclaration.getSqlStatement(sqlId2);
        XSQLDefinition.Sql mysqlSql = xsqlDeclaration.getSqlStatement("mysql", sqlId);

        Assert.assertNotNull(sql);
        Assert.assertNotNull(sql2);
        Assert.assertNotNull(mysqlSql);
        Assert.assertNotNull(sql.getSqlStatement());

        String formattedSql = sql2.getSqlStatement("begin_at", "2019-05-09 00:00:00", "end_at", "2019-10-09 23:59:59");

        Assert.assertNotNull(formattedSql);
        Assert.assertTrue(formattedSql.indexOf("$") == -1);
    }
}
