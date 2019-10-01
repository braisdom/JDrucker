package org.braisdom.drucker.xsql;

import antlr4.XSQLBaseListener;
import antlr4.XSQLLexer;
import antlr4.XSQLParser;
import antlr4.XSQLParser.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.braisdom.drucker.WordUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class XSQLDefinition extends XSQLBaseListener {

    public static class XSQLDeclaration {
        private String dialect;
        private Initialize initialize;
        private Migrations migrations;
        private List<Sql> sqlStatements = new ArrayList<>();
        private Map<String, Map<String, Sql>> globalDialectSqlMap = new HashMap<>();
        private Map<String, Map<String, Sql>> dialectSqlMap = new HashMap<>();
        private Map<String, Sql> sqlMap = new HashMap<>();

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }

        public Initialize getInitialize() {
            return initialize;
        }

        public void setInitialize(Initialize initialize) {
            this.initialize = initialize;
        }

        public Migrations getMigrations() {
            return migrations;
        }

        public void setMigrations(Migrations migrations) {
            this.migrations = migrations;
        }

        public List<Sql> getSqlStatements() {
            return sqlStatements;
        }

        public Sql getSqlStatement(String dialect, String sqlId) {
            Map<String, Sql> tempDialectSqlMap = globalDialectSqlMap.get(dialect);
            if(tempDialectSqlMap == null)
                tempDialectSqlMap = dialectSqlMap.get(dialect);

            if(tempDialectSqlMap == null)
                throw new IllegalArgumentException("Cannot find sql statement with dialect: " + dialect);
            return tempDialectSqlMap.get(sqlId);
        }

        public Sql getSqlStatement(String sqlId) {
            return sqlMap.get(sqlId);
        }

        public void addSqlStatement(Sql sqlStatement) {
            if (!WordUtil.isEmpty(dialect)) {
                Map<String, Sql> innerGlobalDialectSqlMap = new HashMap<>();
                if (globalDialectSqlMap.get(dialect) == null)
                    globalDialectSqlMap.put(dialect, innerGlobalDialectSqlMap);
                innerGlobalDialectSqlMap.put(sqlStatement.id, sqlStatement);
            }

            if (!WordUtil.isEmpty(sqlStatement.dialect)) {
                Map<String, Sql> innerDialectSqlMap = new HashMap<>();
                if (dialectSqlMap.get(sqlStatement.dialect) == null)
                    dialectSqlMap.put(sqlStatement.dialect, innerDialectSqlMap);
                innerDialectSqlMap.put(sqlStatement.id, sqlStatement);
            }

            sqlMap.put(sqlStatement.id, sqlStatement);
            sqlStatements.add(sqlStatement);
        }
    }

    public static class Initialize extends SqlStatementContainer {
        private String tableName;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }
    }

    public static class Migrations {
        private List<Migration> migrations = new ArrayList<>();

        public List<Migration> getMigrations() {
            return migrations;
        }

        public void addMigration(Migration migration) {
            this.migrations.add(migration);
        }
    }

    public static class Migration extends SqlStatementContainer {
        private float version;

        public float getVersion() {
            return version;
        }

        public void setVersion(float version) {
            this.version = version;
        }
    }

    public static class Sql extends SqlStatementContainer {
        private String id;
        private String dialect;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }
    }

    public static class SqlStatementContainer {
        private List<String> sqlStatements = new ArrayList<>();

        public List<String> getSqlStatements() {
            return sqlStatements;
        }

        public String getFirstSqlStatement() {
            if(sqlStatements.size() > 0)
                return sqlStatements.get(0);
            return null;
        }

        public void addSqlStatement(String sqlStatements) {
            this.sqlStatements.add(sqlStatements);
        }
    }

    private static class XSQLErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                int charPositionInLine, String msg, RecognitionException e) {
            throw new XSQLSyntaxError("line " + line + ":" + charPositionInLine + " " + msg.trim());
        }
    }

    private XSQLDeclaration xsqlDeclaration;

    private XSQLDefinition() {
        xsqlDeclaration = new XSQLDeclaration();
    }

    @Override
    public void enterXsqlDecl(XSQLParser.XsqlDeclContext ctx) {
        DialectDeclContext dialectContext = ctx.dialectDecl();
        InitDeclContext initDeclContext = ctx.initDecl();
        MigrationsDeclContext migrationsDeclContext = ctx.migrationsDecl();
        List<SqlDeclContext> sqlDeclContexts = ctx.sqlDecl();

        if (dialectContext != null) {
            if (dialectContext.dialectOption() != null)
                xsqlDeclaration.setDialect(dialectContext.dialectOption().getText());
        }

        if (initDeclContext != null) {
            Initialize initialize = new Initialize();
            initialize.setTableName(initDeclContext.tableName().getText());
            List<TerminalNode> sqls = initDeclContext.sqlBlock().SQL();
            for (TerminalNode sql : sqls)
                initialize.addSqlStatement(sql.getText());
            xsqlDeclaration.setInitialize(initialize);
        }

        if (migrationsDeclContext != null) {
            Migrations migrations = new Migrations();
            List<MigrationsVersionDeclContext> migrationsVersionDeclContexts = ctx.migrationsDecl().migrationsVersionDecl();

            for (MigrationsVersionDeclContext migrationsVersionDeclContext : migrationsVersionDeclContexts) {
                Migration migration = new Migration();
                List<TerminalNode> sqls = migrationsVersionDeclContext.sqlBlock().SQL();
                TerminalNode version = migrationsVersionDeclContext.FLOAT();
                migration.setVersion(Float.valueOf(version.getText()));
                for (TerminalNode sql : sqls)
                    migration.addSqlStatement(sql.getText());
                migrations.addMigration(migration);
            }

            xsqlDeclaration.setMigrations(migrations);
        }

        for (SqlDeclContext sqlDeclContext : sqlDeclContexts) {
            Sql sql = new Sql();
            List<TerminalNode> rawSqls = sqlDeclContext.sqlBlock().SQL();
            if(sqlDeclContext.dialectOption() != null)
                sql.setDialect(sqlDeclContext.dialectOption().getText());
            for (TerminalNode rawSql : rawSqls)
                sql.addSqlStatement(rawSql.getText());
            xsqlDeclaration.addSqlStatement(sql);
        }
    }

    public static XSQLDeclaration parse(String fileName) throws IOException {
        Objects.requireNonNull(fileName, "The fileName cannot be null");
        return parse(fileName, XSQLDefinition.class.getClassLoader());
    }

    public static XSQLDeclaration parse(String fileName, ClassLoader classLoader) throws IOException {
        Objects.requireNonNull(fileName, "The fileName cannot be null");
        Objects.requireNonNull(classLoader, "The classLoader cannot be null");
        return parse(classLoader.getResourceAsStream(fileName));
    }

    public static XSQLDeclaration parse(InputStream inputStream) throws IOException {
        Objects.requireNonNull(inputStream, "The inputStream cannot be null");

        CharStream charStream = CharStreams.fromStream(inputStream);
        XSQLLexer xsqlLexer = new XSQLLexer((charStream));
        CommonTokenStream commonStream = new CommonTokenStream(xsqlLexer);
        XSQLParser xsqlParser = new XSQLParser(commonStream);
        XSQLDefinition xsqlDefinition = new XSQLDefinition();

        xsqlParser.addErrorListener(new XSQLErrorListener());
        xsqlParser.xsqlDecl().enterRule(xsqlDefinition);

        return xsqlDefinition.xsqlDeclaration;
    }
}
