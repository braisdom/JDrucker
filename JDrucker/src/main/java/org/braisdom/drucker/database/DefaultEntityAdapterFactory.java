package org.braisdom.drucker.database;

import java.beans.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DefaultEntityAdapterFactory implements EntityAdapterFactory {

    @Override
    public EntityAdapter createEntityAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException {
        return new EntityAdapterImpl(tableMetaData, resultSet);
    }

    static class EntityAdapterImpl implements EntityAdapter {

        private final TableMetaData tableMetaData;
        private final ResultSet resultSet;
        private final Map<String, Object> columnValueHolder;

        public EntityAdapterImpl(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException {
            this.tableMetaData = tableMetaData;
            this.resultSet = resultSet;
            this.columnValueHolder = new HashMap<>();

            extractRow();
        }

        @Override
        public Object getEntity() throws EntityInstantiateException {
            Class<?> entityBeanClass = tableMetaData.getEntityBeanClass();
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(entityBeanClass);
                Object entity = Beans.instantiate(entityBeanClass.getClassLoader(), entityBeanClass.getName());
                return null;
            }catch (IOException ex) {
                throw new EntityInstantiateException(ex.getMessage(), ex);
            }catch (ClassNotFoundException ex) {
                throw new EntityInstantiateException(ex.getMessage(), ex);
            } catch (IntrospectionException ex) {
                throw new EntityInstantiateException(ex.getMessage(), ex);
            }
        }

        @Override
        public RawEntity getRaw() {
            RawEntity rawEntity = new RawEntity();
            rawEntity.setAttributes(columnValueHolder);
            return rawEntity;
        }

        private void extractRow() throws SQLException {
            String[] columnNames = tableMetaData.getColumnNames();
            for (String columnName : columnNames) {
                TableMetaData.ColumnMetaData columnMetaData = tableMetaData.getColumnMetaData(columnName);
                processValue(columnMetaData);
            }
        }

        private void processValue(TableMetaData.ColumnMetaData columnMetaData) throws SQLException {
            String columnName = columnMetaData.getName();
            int columnIndex = columnMetaData.getIndex();
            switch (columnMetaData.getType()) {
                case Types.CHAR:
                case Types.VARCHAR:
                case Types.LONGVARCHAR:
                case Types.SQLXML:
                    columnValueHolder.put(columnName, resultSet.getString(columnIndex));
                    break;
                case Types.BINARY:
                case Types.VARBINARY:
                case Types.LONGVARBINARY:
                    columnValueHolder.put(columnName, resultSet.getBytes(columnIndex));
                    break;
                case Types.NUMERIC:
                case Types.DECIMAL:
                    columnValueHolder.put(columnName, resultSet.getBigDecimal(columnIndex));
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
