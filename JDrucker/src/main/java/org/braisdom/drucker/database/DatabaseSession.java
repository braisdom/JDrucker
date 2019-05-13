package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Sql;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseSession {

    public EntityAdapter executeQuery(Class<? extends AbstractTable> tableClass, Sql sql) throws SQLException;

    public List<EntityAdapter> executeQueryMany(Class<? extends AbstractTable> tableClass, Sql sql) throws SQLException;

    public int executeUpdate(Class<? extends AbstractTable> tableClass, Sql sql) throws SQLException;

}
