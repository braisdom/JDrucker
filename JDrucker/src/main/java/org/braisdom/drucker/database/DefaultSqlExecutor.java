package org.braisdom.drucker.database;

import javax.sql.DataSource;
import java.util.List;

public class DefaultSqlExecutor implements SqlExecutor {

    private final DataSource dataSource;

    public DefaultSqlExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Object query(String sql) {
        return null;
    }

    @Override
    public List queryList(String sql) {
        return null;
    }

    @Override
    public int update(String sql) {
        return 0;
    }

    @Override
    public TableMetaData getTableMetaData() {
        return null;
    }
}
