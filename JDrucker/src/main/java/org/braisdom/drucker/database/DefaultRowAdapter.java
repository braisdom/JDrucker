package org.braisdom.drucker.database;

import org.braisdom.drucker.database.TableMetaData.ColumnMetaData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DefaultRowAdapter implements RowAdapter {

    private final TableMetaData tableMetaData;
    private final ResultSet resultSet;
    private final Map<String, Object> columnValueHolder;

    public DefaultRowAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException {
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
    public RowAdapter getRawEntity() {
        return null;
    }

    protected void extractRow() throws SQLException {
        String[] columnNames = tableMetaData.getColumnNames();
        for (String columnName : columnNames) {
            ColumnMetaData columnMetaData = tableMetaData.getColumnMetaData(columnName);
            processValue(columnMetaData);
        }
    }

    protected void processValue(ColumnMetaData columnMetaData) throws SQLException {
        String columnName = columnMetaData.getName();
        int columnIndex = columnMetaData.getIndex();
        switch (columnMetaData.getType()) {
            case Types.CHAR:
            case Types.CLOB:
            case Types.NCHAR:
            case Types.NCLOB:
            case Types.VARCHAR:
            case Types.NVARCHAR:
            case Types.LONGVARCHAR:
            case Types.LONGNVARCHAR:
                columnValueHolder.put(columnName, resultSet.getString(columnIndex));
                break;
            case Types.INTEGER:
            case Types.TINYINT:
                columnValueHolder.put(columnName, resultSet.getInt(columnIndex));
                break;
            case Types.FLOAT:
                columnValueHolder.put(columnName, resultSet.getFloat(columnIndex));
                break;
            case Types.DOUBLE:
                columnValueHolder.put(columnName, resultSet.getDouble(columnIndex));
                break;
            case Types.REAL:
            case Types.NUMERIC:
            case Types.DECIMAL:
            case Types.BIGINT:
                columnValueHolder.put(columnName, resultSet.getBigDecimal(columnIndex));
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
                throw new IllegalSQLTypeException("Invalid SQL type: " + columnMetaData.getType());
        }
    }
}
