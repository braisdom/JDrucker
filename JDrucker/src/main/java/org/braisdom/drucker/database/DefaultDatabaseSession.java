package org.braisdom.drucker.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DefaultDatabaseSession implements DatabaseSession {

    private final DataSource dataSource;

    public DefaultDatabaseSession(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RowAdapter executeQuery(String sql) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        return null;
    }

    @Override
    public List<RowAdapter> executeQueryMany(String sql) throws SQLException {
        return null;
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return 0;
    }
}
