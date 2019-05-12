package org.braisdom.drucker.database;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseSession {

    public RowEntityAdapter executeQuery(String sql) throws SQLException;

    public List<RowEntityAdapter> executeQueryMany(String sql) throws SQLException;

    public int executeUpdate(String sql) throws SQLException;

}
