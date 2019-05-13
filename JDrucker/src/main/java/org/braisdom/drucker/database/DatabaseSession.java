package org.braisdom.drucker.database;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseSession {

    public RowEntityAdapter executeQuery(Class<? extends AbstractTable> tableClass, String sql) throws SQLException;

    public List<RowEntityAdapter> executeQueryMany(Class<? extends AbstractTable> tableClass, String sql) throws SQLException;

    public int executeUpdate(Class<? extends AbstractTable> tableClass, String sql) throws SQLException;

}
