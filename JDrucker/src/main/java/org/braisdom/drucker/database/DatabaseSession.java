package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.xsql.XSQLParsingException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseSession {

    String getDatabaseProductName() throws SQLException;

    Connection getRawConnection() throws SQLException;

    TableRow executeQuery(Class<?> tableClass, Class<? extends TableRow> tableRowClass,
                          SQL sql, SQLParameter[] sqlParameters)
            throws SQLException, XSQLParsingException, BeanReflectionException;

    List<TableRow> executeQueryMany(Class<?> tableClass, Class<? extends TableRow> tableRowClass,
                                    SQL sql, SQLParameter[] sqlParameters)
            throws SQLException, XSQLParsingException;

    int executeUpdate(Class<?> tableClass, Class<? extends TableRow> tableRowClass,
                      SQL sql, SQLParameter[] sqlParameters)
            throws SQLException;

}
