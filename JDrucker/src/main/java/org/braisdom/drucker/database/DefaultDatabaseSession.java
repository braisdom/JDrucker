package org.braisdom.drucker.database;

import org.braisdom.drucker.WordUtil;
import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.Table;
import org.braisdom.drucker.xsql.XSQLContext;
import org.braisdom.drucker.xsql.XSQLParser;
import org.braisdom.drucker.xsql.XSQLParsingException;

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
    public EntityAdapter executeQuery(Class<?> tableClass, Class<?> declaringClass,
                                      SQL sql, SQLParameter[] sqlParameters) throws SQLException, XSQLParsingException {
        Connection connection = databaseConnectionFactory.getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            Table table = tableClass.getAnnotation(Table.class);
            String tableName = getTableName(table);
            String fileName = getXsqlFileName(declaringClass, table);
            XSQLContext xsqlContext = createXSqlContext(tableName, sqlParameters);
            String sqlStatement = XSQLParser.parse(fileName, sql.id(),
                    declaringClass, xsqlContext.toFreemarkContext());
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement);

            TableMetaData tableMetaData = getTableMetaData(tableClass, databaseMetaData, resultSet.getMetaData());
            return rowAdapterFactory.createRowAdapter(tableMetaData, resultSet);
        } finally {
            close(statement, resultSet, connection);
        }
    }

    @Override
    public List<EntityAdapter> executeQueryMany(Class<?> tableClass, Class<?> declaringClass,
                                                SQL sql, SQLParameter[] sqlParameters) throws SQLException, XSQLParsingException {
        Connection connection = databaseConnectionFactory.getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            Table table = tableClass.getAnnotation(Table.class);
            String tableName = getTableName(table);
            String fileName = getXsqlFileName(declaringClass, table);
            XSQLContext xsqlContext = createXSqlContext(tableName, sqlParameters);
            String sqlStatement = XSQLParser.parse(fileName, sql.id(),
                    declaringClass, xsqlContext.toFreemarkContext());
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement);

            TableMetaData tableMetaData = getTableMetaData(tableClass, databaseMetaData, resultSet.getMetaData());
            rowAdapterFactory.createRowAdapter(tableMetaData, resultSet);
            return null;
        } finally {
            close(statement, resultSet, connection);
        }
    }

    @Override
    public int executeUpdate(Class<?> tableClass, Class<?> declaringClass,
                             SQL sql, SQLParameter[] sqlParameters) throws SQLException {
        return 0;
    }

    protected TableMetaData getTableMetaData(Class<?> tableClass, DatabaseMetaData databaseMetaData,
                                             ResultSetMetaData resultSetMetaData) throws SQLException {
        if (!tableMetaDataMap.containsKey(tableClass.getName())) {
            TableMetaData tableMetaData = tableMetaDataFactory.createTableMetaData(tableClass, databaseMetaData,
                    resultSetMetaData);
            tableMetaDataMap.put(tableClass.getName(), tableMetaData);
        }
        return tableMetaDataMap.get(tableClass.getName());
    }

    private String getTableName(Table table) {
        String rawTableName = table.tableName();
        if (WordUtil.isEmpty(rawTableName)) {
            String className = table.entityBeanClass().getSimpleName();
            return WordUtil.tableize(WordUtil.replaceLast(className, "Table", ""));
        }
        return table.tableName();
    }

    private String getXsqlFileName(Class<?> declaringClass, Table table) {
        if (AbstractTable.class.equals(declaringClass))
            return declaringClass.getAnnotation(Table.class).file();
        return table.file();
    }

    private XSQLContext createXSqlContext(String tableName, SQLParameter[] sqlParameters) {
        XSQLContext xsqlContext = new XSQLContext();
        xsqlContext.addAttribute("table_name", tableName);
        for (SQLParameter sqlParameter : sqlParameters)
            xsqlContext.addAttribute(sqlParameter.getParam().value(), sqlParameter.getValue());
        return xsqlContext;
    }

    private void close(Statement statement, ResultSet resultSet, Connection connection) throws SQLException {
        if (statement != null)
            statement.close();
        if (resultSet != null)
            resultSet.close();
        if (connection != null)
            connection.close();
    }
}
