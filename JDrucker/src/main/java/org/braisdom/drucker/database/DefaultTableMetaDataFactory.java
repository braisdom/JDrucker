package org.braisdom.drucker.database;

import org.braisdom.drucker.WordUtil;
import org.braisdom.drucker.annotation.Table;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTableMetaDataFactory implements TableMetaDataFactory {

    @Override
    public TableMetaData getTableDescriptor(Class<? extends AbstractTable> tableClass,
                                            ResultSetMetaData resultSetMetaData) throws SQLException {
        return new TableMetaDataImpl(tableClass, resultSetMetaData);
    }

    protected class TableMetaDataImpl implements TableMetaData {

        private final Table tableAnnotation;
        private final Class<? extends AbstractTable> tableClass;
        private final ResultSetMetaData resultSetMetaData;
        private final Map<String, ColumnMetaData> columnMetaDataMap;
        private final String[] columnNames;

        public TableMetaDataImpl(Class<? extends AbstractTable> tableClass,
                                 ResultSetMetaData resultSetMetaData) throws SQLException {
            this.tableClass = tableClass;
            this.resultSetMetaData = resultSetMetaData;
            this.tableAnnotation = tableClass.getAnnotation(Table.class);
            this.columnMetaDataMap = new HashMap<>();
            this.columnNames = prepareColumnMetaData().toArray(new String[]{});

            if (this.tableAnnotation == null)
                throw new IllegalArgumentException("Class " + tableClass.getName() + " has no Table annotation.");
        }

        @Override
        public String getName() {
            String rawTableName = tableAnnotation.tableName();
            if (rawTableName == null || rawTableName.length() == 0)
                return WordUtil.tableize(tableAnnotation.entityBeanClass().getSimpleName());
            return rawTableName;
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
        public Class<? extends AbstractTable> getEntityBeanClass() {
            return tableClass;
        }

        private List<String> prepareColumnMetaData() throws SQLException {
            int columnCount = resultSetMetaData.getColumnCount();
            List<String> columnMetaDatas = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                ColumnMetaData columnMetaData = new ColumnMetaData(resultSetMetaData.getColumnName(i),
                        resultSetMetaData.getColumnType(i), i, resultSetMetaData.getScale(i),
                        resultSetMetaData.isNullable(i));
                columnMetaDatas.add(columnName);
                columnMetaDataMap.put(columnName, columnMetaData);
            }
            return columnMetaDatas;
        }
    }
}