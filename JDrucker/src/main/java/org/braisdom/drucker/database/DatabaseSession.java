package org.braisdom.drucker.database;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseSession {

    public RowAdapter executeQuery(Class<? extends AbstractTable> tableClass, String sql) throws SQLException;

    public List<RowAdapter> executeQueryMany(Class<? extends AbstractTable> tableClass, String sql) throws SQLException;

    public int executeUpdate(Class<? extends AbstractTable> tableClass, String sql) throws SQLException;

}
