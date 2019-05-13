package org.braisdom.drucker.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DefaultDatabaseSession implements DatabaseSession {

    private final DatabaseConnectionFactory databaseConnectionFactory;
    private final TableMetaDataFactory tableMetaDataFactory;

    public DefaultDatabaseSession(DatabaseConnectionFactory databaseConnectionFactory, TableMetaDataFactory tableMetaDataFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
        this.tableMetaDataFactory = tableMetaDataFactory;
    }

    @Override
    public RowEntityAdapter executeQuery(Class<? extends AbstractTable> tableClass, String sql) throws SQLException {
        Connection connection = databaseConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            TableMetaData tableMetaData = tableMetaDataFactory.getTableDescriptor(tableClass, resultSet.getMetaData());
            return null;
        } finally {
            connection.close();
        }
    }

    @Override
    public List<RowEntityAdapter> executeQueryMany(Class<? extends AbstractTable> tableClass, String sql) throws SQLException {
        return null;
    }

    @Override
    public int executeUpdate(Class<? extends AbstractTable> tableClass, String sql) throws SQLException {
        return 0;
    }
}
