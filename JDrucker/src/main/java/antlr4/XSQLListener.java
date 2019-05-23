// Generated from /Users/braisdom/Workspace/JDrucker/JDrucker/src/main/java/XSQL.g4 by ANTLR 4.7.2
package antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XSQLParser}.
 */
public interface XSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XSQLParser#xsqlDecl}.
	 * @param ctx the parse tree
	 */
	void enterXsqlDecl(XSQLParser.XsqlDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#xsqlDecl}.
	 * @param ctx the parse tree
	 */
	void exitXsqlDecl(XSQLParser.XsqlDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link XSQLParser#dialectDecl}.
	 * @param ctx the parse tree
	 */
	void enterDialectDecl(XSQLParser.DialectDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#dialectDecl}.
	 * @param ctx the parse tree
	 */
	void exitDialectDecl(XSQLParser.DialectDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link XSQLParser#initDecl}.
	 * @param ctx the parse tree
	 */
	void enterInitDecl(XSQLParser.InitDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#initDecl}.
	 * @param ctx the parse tree
	 */
	void exitInitDecl(XSQLParser.InitDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link XSQLParser#migrationsDecl}.
	 * @param ctx the parse tree
	 */
	void enterMigrationsDecl(XSQLParser.MigrationsDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#migrationsDecl}.
	 * @param ctx the parse tree
	 */
	void exitMigrationsDecl(XSQLParser.MigrationsDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link XSQLParser#sqlDecl}.
	 * @param ctx the parse tree
	 */
	void enterSqlDecl(XSQLParser.SqlDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#sqlDecl}.
	 * @param ctx the parse tree
	 */
	void exitSqlDecl(XSQLParser.SqlDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link XSQLParser#migrationsVersionDecl}.
	 * @param ctx the parse tree
	 */
	void enterMigrationsVersionDecl(XSQLParser.MigrationsVersionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#migrationsVersionDecl}.
	 * @param ctx the parse tree
	 */
	void exitMigrationsVersionDecl(XSQLParser.MigrationsVersionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link XSQLParser#dialectOption}.
	 * @param ctx the parse tree
	 */
	void enterDialectOption(XSQLParser.DialectOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#dialectOption}.
	 * @param ctx the parse tree
	 */
	void exitDialectOption(XSQLParser.DialectOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XSQLParser#sqlBlock}.
	 * @param ctx the parse tree
	 */
	void enterSqlBlock(XSQLParser.SqlBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#sqlBlock}.
	 * @param ctx the parse tree
	 */
	void exitSqlBlock(XSQLParser.SqlBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link XSQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(XSQLParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XSQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(XSQLParser.TableNameContext ctx);
}