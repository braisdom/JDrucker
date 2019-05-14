package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseSession {

    public EntityAdapter executeQuery(Class<? extends AbstractTable> tableClass,
                                      SQL sql, SQLExecuteContext sqlExecuteContext) throws SQLException;

    public List<EntityAdapter> executeQueryMany(Class<? extends AbstractTable> tableClass,
                                                SQL sql, SQLExecuteContext sqlExecuteContext) throws SQLException;

    public int executeUpdate(Class<? extends AbstractTable> tableClass, SQL sql) throws SQLException;

}
