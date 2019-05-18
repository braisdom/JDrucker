package org.braisdom.drucker.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DefaultRowAdapterFactory implements RowAdapterFactory {

    @Override
    public EntityAdapter createRowAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException {
        return new DefaultEntityAdapter(tableMetaData, resultSet);
    }

    private class DefaultEntityAdapter implements EntityAdapter {

        private final TableMetaData tableMetaData;
        private final ResultSet resultSet;
        private final Map<String, Object> columnValueHolder;

        public DefaultEntityAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException {
            this.tableMetaData = tableMetaData;
            this.resultSet = resultSet;
            this.columnValueHolder = new HashMap<>();

            extractRow();
        }

        @Override
        public Object getEntity() {
            return null;
        }

        @Override
        public RawEntity getRawEntity() {
            return null;
        }

        protected void extractRow() throws SQLException {
            String[] columnNames = tableMetaData.getColumnNames();
            for (String columnName : columnNames) {
                TableMetaData.ColumnMetaData columnMetaData = tableMetaData.getColumnMetaData(columnName);
                processValue(columnMetaData);
            }
        }

        protected void processValue(TableMetaData.ColumnMetaData columnMetaData) throws SQLException {
            String columnName = columnMetaData.getName();
            int columnIndex = columnMetaData.getIndex();
            switch (columnMetaData.getType()) {
                case Types.CHAR:
                case Types.VARCHAR:
                case Types.LONGVARCHAR:
                case Types.SQLXML:
                    columnValueHolder.put(columnName, resultSet.getString(columnIndex));
                    break;
                case Types.NUMERIC:
                case Types.DECIMAL:
                    columnValueHolder.put(columnName, resultSet.getBigDecimal(columnIndex));
                    break;
                case Types.BINARY:
                case Types.VARBINARY:
                case Types.LONGVARBINARY:
                    columnValueHolder.put(columnName, resultSet.getBytes(columnIndex));
                    break;
                case Types.FLOAT:
                case Types.DOUBLE:
                    columnValueHolder.put(columnName, resultSet.getDouble(columnIndex));
                    break;
                case Types.SMALLINT:
                case Types.TINYINT:
                    columnValueHolder.put(columnName, resultSet.getShort(columnIndex));
                    break;
                case Types.BIT:
                case Types.BOOLEAN:
                    columnValueHolder.put(columnName, resultSet.getBoolean(columnIndex));
                    break;
                case Types.BIGINT:
                    columnValueHolder.put(columnName, resultSet.getLong(columnIndex));
                    break;
                case Types.REAL:
                    columnValueHolder.put(columnName, resultSet.getFloat(columnIndex));
                    break;
                case Types.INTEGER:
                    columnValueHolder.put(columnName, resultSet.getInt(columnIndex));
                    break;
                case Types.DATE:
                    columnValueHolder.put(columnName, resultSet.getDate(columnIndex));
                    break;
                case Types.TIME:
                    columnValueHolder.put(columnName, resultSet.getTime(columnIndex));
                    break;
                case Types.TIMESTAMP:
                    columnValueHolder.put(columnName, resultSet.getTimestamp(columnIndex));
                    break;
                default:
                    throw new IllegalSQLTypeException("Invalid SQL type: " + columnName + ":" + columnMetaData.getTypeName());
            }
        }
    }

}
