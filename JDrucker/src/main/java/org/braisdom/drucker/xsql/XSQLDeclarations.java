package org.braisdom.drucker.xsql;

import antlr4.XSQLBaseListener;
import antlr4.XSQLLexer;
import antlr4.XSQLParser;
import antlr4.XSQLParser.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class XSQLDeclarations extends XSQLBaseListener implements ANTLRErrorListener {

    public static class XSQLDeclaration {
        private String dialect;
        private InitializeDeclaration initializeDeclaration;
        private Migrations migrations;
        private List<Sql> sqlStatements = new ArrayList<>();

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }

        public InitializeDeclaration getInitializeDeclaration() {
            return initializeDeclaration;
        }

        public void setInitializeDeclaration(InitializeDeclaration initializeDeclaration) {
            this.initializeDeclaration = initializeDeclaration;
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

        public void addSqlStatement(Sql sqlStatement) {
            this.sqlStatements.add(sqlStatement);
        }
    }

    public static class InitializeDeclaration extends SqlStatementContainer {
        private String tableName;
        private List<String> sqlStatements = new ArrayList<>();

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

        public void aaMigration(Migration migration) {
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

        public void addSqlStatement(String sqlStatements) {
            this.sqlStatements.add(sqlStatements);
        }
    }

    private XSQLDeclaration xsqlDeclaration;

    private XSQLDeclarations() {
        xsqlDeclaration = new XSQLDeclaration();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        throw new XSQLSyntaxError("line " + line + ":" + charPositionInLine + " " + msg.trim());
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
                                boolean exact, BitSet ambigAlts, ATNConfigSet configs) {

    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex,
                                            int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {

    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
                                         int prediction, ATNConfigSet configs) {

    }

    @Override
    public void enterXsqlDecl(XSQLParser.XsqlDeclContext ctx) {
        DialectDeclContext declContext = ctx.dialectDecl();
        InitDeclContext initDeclContext = ctx.initDecl();
        MigrationsDeclContext migrationsDeclContext = ctx.migrationsDecl();
        List<SqlDeclContext> sqlDeclContexts = ctx.sqlDecl();
        if (declContext != null) {
            if (declContext.dialectOption() != null)
                xsqlDeclaration.setDialect(declContext.dialectOption().getText());
        }

        if(initDeclContext != null) {
            InitializeDeclaration initializeDeclaration = new InitializeDeclaration();
            initializeDeclaration.setTableName(initDeclContext.tableName().getText());
            List<TerminalNode> sqls = initDeclContext.sqlBlock().SQL();
            for(TerminalNode sql : sqls)
                initializeDeclaration.addSqlStatement(sql.getText());
            xsqlDeclaration.setInitializeDeclaration(initializeDeclaration);
        }

        if(migrationsDeclContext != null) {
            Migrations migrations = new Migrations();
            List<MigrationsVersionDeclContext> migrationsVersionDeclContexts = ctx.migrationsDecl().migrationsVersionDecl();

            for(MigrationsVersionDeclContext migrationsVersionDeclContext : migrationsVersionDeclContexts) {
                Migration migration = new Migration();
                List<TerminalNode> sqls = migrationsVersionDeclContext.sqlBlock().SQL();
                TerminalNode version = migrationsVersionDeclContext.FLOAT();
                migration.setVersion(Float.valueOf(version.getText()));
                for(TerminalNode sql : sqls)
                    migration.addSqlStatement(sql.getText());
                migrations.aaMigration(migration);
            }

            xsqlDeclaration.setMigrations(migrations);
        }

        for(SqlDeclContext sqlDeclContext : sqlDeclContexts) {
            Sql sql = new Sql();
            List<TerminalNode> rawSqls = sqlDeclContext.sqlBlock().SQL();
            sql.setDialect(sqlDeclContext.dialectOption().getText());
            List<String> sqlStatements = new ArrayList<>();
            for(TerminalNode rawSql : rawSqls)
                sqlStatements.add(rawSql.getText());
            xsqlDeclaration.addSqlStatement(sql);
        }
    }

    public static XSQLDeclaration parse(InputStream inputStream) throws IOException {
        CharStream charStream = CharStreams.fromStream(inputStream);
        XSQLLexer xsqlLexer = new XSQLLexer((charStream));
        CommonTokenStream commonStream = new CommonTokenStream(xsqlLexer);
        XSQLParser xsqlParser = new XSQLParser(commonStream);
        XSQLDeclarations xsqlDeclarations = new XSQLDeclarations();
        xsqlParser.addErrorListener(xsqlDeclarations);
        xsqlParser.xsqlDecl().enterRule(xsqlDeclarations);
        return xsqlDeclarations.xsqlDeclaration;
    }
}
