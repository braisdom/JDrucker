package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.xsql.XSQLParsingException;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseSession {

    public EntityAdapter executeQuery(Class<?> tableClass, Class<?> declaringClass,
                                      SQL sql, SQLParameter[] sqlParameters) throws SQLException, XSQLParsingException;

    public List<EntityAdapter> executeQueryMany(Class<?> tableClass, Class<?> declaringClass,
                                                SQL sql, SQLParameter[] sqlParameters) throws SQLException, XSQLParsingException;

    public int executeUpdate(Class<?> tableClass, Class<?> declaringClass, SQL sql,
                             SQLParameter[] sqlParameters) throws SQLException;

}
