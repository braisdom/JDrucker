// Generated from /Users/braisdom/Workspace/JDrucker/JDrucker/src/main/java/XSQL.g4 by ANTLR 4.7.2
package antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XSQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XSQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XSQLParser#xsqlDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXsqlDecl(XSQLParser.XsqlDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link XSQLParser#dialectDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDialectDecl(XSQLParser.DialectDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link XSQLParser#initDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitDecl(XSQLParser.InitDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link XSQLParser#migrationsDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMigrationsDecl(XSQLParser.MigrationsDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link XSQLParser#sqlDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlDecl(XSQLParser.SqlDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link XSQLParser#migrationsVersionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMigrationsVersionDecl(XSQLParser.MigrationsVersionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link XSQLParser#dialectOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDialectOption(XSQLParser.DialectOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XSQLParser#sqlBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlBlock(XSQLParser.SqlBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link XSQLParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(XSQLParser.TableNameContext ctx);
}