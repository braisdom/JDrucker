package org.braisdom.drucker.database;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDatabaseSession implements DatabaseSession {

    private final DatabaseConnectionFactory databaseConnectionFactory;
    private final TableMetaDataFactory tableMetaDataFactory;
    private final Map<String, TableMetaData> tableMetaDataMap;
    private final RowAdapterFactory rowAdapterFactory;

    public DefaultDatabaseSession(DatabaseConnectionFactory databaseConnectionFactory,
                                  TableMetaDataFactory tableMetaDataFactory,
                                  RowAdapterFactory rowAdapterFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
        this.tableMetaDataFactory = tableMetaDataFactory;
        this.rowAdapterFactory = rowAdapterFactory;
        this.tableMetaDataMap = new HashMap<>();
    }

    @Override
    public RowAdapter executeQuery(Class<? extends AbstractTable> tableClass, String sql) throws SQLException {
        Connection connection = databaseConnectionFactory.getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            TableMetaData tableMetaData = getTableMetaData(tableClass, databaseMetaData, resultSet.getMetaData());
            return rowAdapterFactory.createRowAdapter(tableMetaData, resultSet);
        } finally {
            close(statement, resultSet, connection);
        }
    }

    @Override
    public List<RowAdapter> executeQueryMany(Class<? extends AbstractTable> tableClass, String sql) throws SQLException {
        return null;
    }

    @Override
    public int executeUpdate(Class<? extends AbstractTable> tableClass, String sql) throws SQLException {
        return 0;
    }

    protected TableMetaData getTableMetaData(Class<? extends AbstractTable> tableClass, DatabaseMetaData databaseMetaData,
                                             ResultSetMetaData resultSetMetaData) throws SQLException {
        if (!tableMetaDataMap.containsKey(tableClass.getName())) {
            TableMetaData tableMetaData = tableMetaDataFactory.createTableMetaData(tableClass, databaseMetaData,
                    resultSetMetaData);
            tableMetaDataMap.put(tableClass.getName(), tableMetaData);
        }
        return tableMetaDataMap.get(tableClass.getName());
    }

    private void close(Statement statement, ResultSet resultSet, Connection connection) throws SQLException {
        if(statement != null)
            statement.close();
        if (resultSet != null)
            resultSet.close();
        if(connection != null)
            connection.close();
    }
}
