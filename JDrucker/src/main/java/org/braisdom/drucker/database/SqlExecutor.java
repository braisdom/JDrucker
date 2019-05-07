package org.braisdom.drucker.database;

import java.sql.SQLException;
import java.util.List;

public interface SqlExecutor {

    Object query(String sql);

    List queryList(String sql);

    int update(String sql);

    TableMetaData getTableMetaData() throws SQLException;
}
