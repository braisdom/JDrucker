package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Table;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTableMetaDataFactory implements TableMetaDataFactory {

    @Override
    public TableMetaData createTableMetaData(Class<?> tableClass,
                                             DatabaseMetaData databaseMetaData,
                                             ResultSetMetaData resultSetMetaData) throws SQLException {
        return new TableMetaDataImpl(tableClass, databaseMetaData, resultSetMetaData);
    }

    protected static class TableMetaDataImpl implements TableMetaData {

        private final Map<String, ColumnMetaData> columnMetaDataMap;
        private final String[] columnNames;

        private final Table tableAnnotation;
        private final Class<?> tableClass;
        private final DatabaseMetaData databaseMetaData;
        private final ResultSetMetaData resultSetMetaData;

        public TableMetaDataImpl(Class<?> tableClass,
                                 DatabaseMetaData databaseMetaData,
                                 ResultSetMetaData resultSetMetaData) throws SQLException {
            this.tableClass = tableClass;
            this.databaseMetaData = databaseMetaData;
            this.resultSetMetaData = resultSetMetaData;
            this.tableAnnotation = tableClass.getAnnotation(Table.class);
            this.columnMetaDataMap = new HashMap<>();
            this.columnNames = prepareColumnMetaData().toArray(new String[]{});

            if (this.tableAnnotation == null)
                throw new IllegalArgumentException("Class " + tableClass.getName() + " has no Table annotation.");
        }

        @Override
        public String getDatabaseProductName() throws SQLException {
            return databaseMetaData.getDatabaseProductName();
        }

        @Override
        public String[] getColumnNames() {
            return columnNames;
        }

        @Override
        public ColumnMetaData getColumnMetaData(String columnName) {
            return columnMetaDataMap.get(columnName);
        }

        @Override
        public Class<?> getEntityBeanClass() {
            return tableClass;
        }

        private List<String> prepareColumnMetaData() throws SQLException {
            int columnCount = resultSetMetaData.getColumnCount();
            List<String> columnMetaDatas = new ArrayList<>();

            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                String columnName = resultSetMetaData.getColumnName(columnIndex);
                ColumnMetaData columnMetaData = new ColumnMetaData(resultSetMetaData.getColumnName(columnIndex), columnIndex,
                        resultSetMetaData.getColumnType(columnIndex), resultSetMetaData.getColumnTypeName(columnIndex),
                        resultSetMetaData.getScale(columnIndex), resultSetMetaData.isNullable(columnIndex));
                columnMetaDatas.add(columnName);
                columnMetaDataMap.put(columnName, columnMetaData);
            }
            return columnMetaDatas;
        }
    }
}