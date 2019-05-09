package org.braisdom.drucker.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlExecutor implements SqlExecutor {

    private final DataSource dataSource;

    public DefaultSqlExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Object query(String sql) throws SQLException {
        return null;
    }

    @Override
    public List queryList(String sql) throws SQLException {
        return null;
    }

    @Override
    public int update(String sql) throws SQLException {
        return 0;
    }

    @Override
    public TableMetaData getTableMetaData(String tableName) throws SQLException {
        return null;
    }
}
