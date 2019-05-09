package org.braisdom.drucker.database;

import java.sql.SQLException;
import java.util.List;

public interface SqlExecutor {

    Object query(String sql) throws SQLException;

    List queryList(String sql) throws SQLException;

    int update(String sql) throws SQLException;

    TableMetaData getTableMetaData(String tableName) throws SQLException;
}
