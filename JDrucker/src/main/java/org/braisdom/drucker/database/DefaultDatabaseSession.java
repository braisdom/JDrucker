package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.Table;
import org.braisdom.drucker.xsql.XSqlContext;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDatabaseSession implements DatabaseSession {

    private final DatabaseConnectionFactory databaseConnectionFactory;
    private final TableMetaDataFactory tableMetaDataFactory;
    private final RowAdapterFactory rowAdapterFactory;
    private final Map<String, TableMetaData> tableMetaDataMap;

    public DefaultDatabaseSession(DatabaseConnectionFactory databaseConnectionFactory,
                                  TableMetaDataFactory tableMetaDataFactory,
                                  RowAdapterFactory rowAdapterFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
        this.tableMetaDataFactory = tableMetaDataFactory;
        this.rowAdapterFactory = rowAdapterFactory;
        this.tableMetaDataMap = new HashMap<>();
    }

    @Override
    public EntityAdapter executeQuery(Class<? extends AbstractTable> tableClass,
                                      SQL sql, SQLExecutionContext sqlExecutionContext) throws SQLException {
        Table tableAnnotation = tableClass.getAnnotation(Table.class);
        Connection connection = databaseConnectionFactory.getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
            TableMetaData tableMetaData = getTableMetaData(tableClass, databaseMetaData, resultSet.getMetaData());
            return rowAdapterFactory.createRowAdapter(tableMetaData, resultSet);
        } finally {
            close(statement, resultSet, connection);
        }
    }

    @Override
    public List<EntityAdapter> executeQueryMany(Class<? extends AbstractTable> tableClass,
                                                SQL sql, SQLExecutionContext sqlExecutionContext) throws SQLException {
        Connection connection = databaseConnectionFactory.getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
            TableMetaData tableMetaData = getTableMetaData(tableClass, databaseMetaData, resultSet.getMetaData());
            rowAdapterFactory.createRowAdapter(tableMetaData, resultSet);
            return null;
        } finally {
            close(statement, resultSet, connection);
        }
    }

    @Override
    public int executeUpdate(Class<? extends AbstractTable> tableClass, SQL sql) throws SQLException {
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

    private XSqlContext createXSqlContext() {
        return null;
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
