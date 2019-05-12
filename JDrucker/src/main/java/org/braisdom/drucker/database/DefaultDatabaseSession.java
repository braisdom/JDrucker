package org.braisdom.drucker.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DefaultDatabaseSession implements DatabaseSession {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    public DefaultDatabaseSession(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    @Override
    public RowEntityAdapter executeQuery(String sql) throws SQLException {
        Connection connection = databaseConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        return null;
    }

    @Override
    public List<RowEntityAdapter> executeQueryMany(String sql) throws SQLException {
        return null;
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return 0;
    }
}
