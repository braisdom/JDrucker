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
import java.util.Objects;

public class DefaultDatabaseSession implements DatabaseSession {

    private final DatabaseConnectionFactory databaseConnectionFactory;
    private final TableMetaDataFactory tableMetaDataFactory;
    private final TableRowFactory tableRowFactory;
    private final Map<String, TableMetaData> tableMetaDataMap;

    public DefaultDatabaseSession(DatabaseConnectionFactory databaseConnectionFactory,
                                  TableMetaDataFactory tableMetaDataFactory,
                                  TableRowFactory tableRowFactory) {
        Objects.requireNonNull(databaseConnectionFactory, "databaseConnectionFactory cannot be null");
        Objects.requireNonNull(tableMetaDataFactory, "tableMetaDataFactory cannot be null");
        Objects.requireNonNull(tableRowFactory, "tableRowFactory cannot be null");

        this.databaseConnectionFactory = databaseConnectionFactory;
        this.tableMetaDataFactory = tableMetaDataFactory;
        this.tableRowFactory = tableRowFactory;
        this.tableMetaDataMap = new HashMap<>();
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        return databaseConnectionFactory.getConnection().getMetaData().getDatabaseProductName();
    }

    @Override
    public Connection getRawConnection() throws SQLException {
        return databaseConnectionFactory.getConnection();
    }

    @Override
    public TableRow executeQuery(Class<?> tableClass, Class<? extends TableRow> tableRowClass,
                                 SQL sql, SQLParameter[] sqlParameters)
            throws SQLException, XSQLParsingException, BeanReflectionException {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            Table table = tableClass.getAnnotation(Table.class);
            String tableName = getTableName(table, tableClass);
            String fileName = getXsqlFileName(sql, table, tableClass);

            XSQLContext xsqlContext = createXSqlContext(tableName, sqlParameters);
            String sqlStatement = XSQLParser.parse(fileName, sql.id(), tableClass, xsqlContext.toFreemarkContext());

            connection = databaseConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement);
            // The resultSet is closed while the empty resultSet appeared
            if(resultSet.isClosed())
                return null;

            TableMetaData tableMetaData = getTableMetaData(tableClass, connection.getMetaData(),
                    resultSet.getMetaData());

            return tableRowFactory.createTableRow(tableRowClass, tableMetaData, resultSet);
        } finally {
            close(statement, resultSet, connection);
        }
    }

    @Override
    public List<TableRow> executeQueryMany(Class<?> tableClass, Class<? extends TableRow> tableRowClass,
                                           SQL sql, SQLParameter[] sqlParameters) throws SQLException, XSQLParsingException {
        return null;
    }

    @Override
    public int executeUpdate(Class<?> tableClass, Class<? extends TableRow> tableRowClass,
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

    private String getTableName(Table table, Class<?> tableClass) {
        String rawTableName = table.tableName();
        if (WordUtil.isEmpty(rawTableName)) {
            String className = tableClass.getSimpleName();
            return WordUtil.tableize(className);
        }
        return rawTableName;
    }

    private String getXsqlFileName(SQL sql, Table table, Class<?> tableClass) {
        if (sql.primitive())
            return TableBehavior.class.getAnnotation(Table.class).file();
        else if(WordUtil.isEmpty(table.file()))
            return "/xsql/" + WordUtil.tableize(tableClass.getSimpleName()) +".xsql";
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
