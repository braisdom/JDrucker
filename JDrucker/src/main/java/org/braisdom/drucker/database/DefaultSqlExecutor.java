package org.braisdom.drucker.database;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlExecutor implements SqlExecutor {

    private final DataSource dataSource;

    public DefaultSqlExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Object query(String sql) throws SQLException {
        return null;
    }

    public List queryList(String sql) throws SQLException {
        return null;
    }

    public int update(String sql) throws SQLException {
        return 0;
    }

    public TableMetaData getTableMetaData(String tableName) throws SQLException {
        return null;
    }
}
