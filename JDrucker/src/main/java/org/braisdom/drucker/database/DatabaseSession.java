package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseSession {

    public EntityAdapter executeQuery(Class<?> tableClass, Class<?> declaringClass,
                                      SQL sql, SQLExecutionContext sqlExecutionContext) throws SQLException;

    public List<EntityAdapter> executeQueryMany(Class<?> tableClass, Class<?> declaringClass,
                                                SQL sql, SQLExecutionContext sqlExecutionContext) throws SQLException;

    public int executeUpdate(Class<?> tableClass, Class<?> declaringClass, SQL sql) throws SQLException;

}
