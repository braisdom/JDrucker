package org.braisdom.drucker.database;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.braisdom.drucker.WordUtil;
import org.braisdom.drucker.annotation.Proxied;
import org.braisdom.drucker.annotation.TableRowBean;

import java.beans.*;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class DefaultTableRowFactory implements TableRowFactory {

    @Override
    public TableRow createTableRow(Class<? extends TableRow> tableRowClass,
                                   TableMetaData tableMetaData,
                                   ResultSet resultSet) throws SQLException, BeanReflectionException {
        Enhancer enhancer = new Enhancer();
        TableRowImpl tableRowImpl = new TableRowImpl(tableRowClass, tableMetaData, resultSet);
        TableRowProxy tableRowProxy = new TableRowProxy(tableRowImpl);

        enhancer.setSuperclass(tableRowClass);
        enhancer.setCallback(tableRowProxy);

        return tableRowClass.cast(enhancer.create());
    }

    private static class TableRowProxy implements MethodInterceptor {

        private final TableRowImpl tableRowImpl;

        public TableRowProxy(TableRowImpl tableRowImpl) {
            this.tableRowImpl = tableRowImpl;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (method.getAnnotation(Proxied.class) == null)
                return proxy.invokeSuper(obj, args);
            return method.invoke(tableRowImpl, args);
        }

    }

    private static class TableRowImpl extends AbstractTableRow {

        private static final Map<String, Map<String, PropertyDescriptor>> beanInfoMap = new HashMap<>();

        private final Class<? extends TableRow> tableRowClass;
        private final TableMetaData tableMetaData;
        private final ResultSet resultSet;
        private final Map<String, Object> columnValueHolder;

        public TableRowImpl(Class<? extends TableRow> tableRowClass,
                            TableMetaData tableMetaData, ResultSet resultSet) throws SQLException, BeanReflectionException {
            this.tableRowClass = tableRowClass;
            this.tableMetaData = tableMetaData;
            this.resultSet = resultSet;
            this.columnValueHolder = new HashMap<>();

            processRow();

            if (tableRowClass.getAnnotation(TableRowBean.class) != null) {
                cacheBeanInfo(tableRowClass);
                processBeanPropertyValue();
            }
        }

        @Override
        public TableMetaData.ColumnMetaData getColumnMetaData(String columnName) {
            return tableMetaData.getColumnMetaData(columnName);
        }

        @Override
        public Map<String, Object> getAttributes() {
            return columnValueHolder;
        }

        private void processBeanPropertyValue() throws BeanReflectionException {
            Map<String, PropertyDescriptor> propertyDescriptorMap = beanInfoMap.get(tableRowClass.getName());
            if (propertyDescriptorMap != null) {
                for (Map.Entry<String, Object> entry : columnValueHolder.entrySet()) {
                    String propertyName = WordUtil.camelize(entry.getKey(), true);
                    PropertyDescriptor propertyDescriptor = propertyDescriptorMap.get(propertyName);
                    if (propertyDescriptor != null) {
                        try {
                            Object propertyValue = columnValueHolder.get(entry.getKey());
                            propertyDescriptor.getWriteMethod().invoke(this, propertyValue);
                        } catch (Exception ex) {
                            throw new BeanReflectionException(ex.getMessage(), ex);
                        }
                    }
                }
            }
        }

        private void cacheBeanInfo(Class<? extends TableRow> tableRowClass) throws BeanReflectionException {
            try {
                String className = tableRowClass.getName();
                if (beanInfoMap.get(className) == null) {
                    Map<String, PropertyDescriptor> propertyDescriptorMap = new HashMap<>();
                    BeanInfo beanInfo = Introspector.getBeanInfo(tableRowClass);
                    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                    for (PropertyDescriptor propertyDescriptor : propertyDescriptors)
                        propertyDescriptorMap.put(propertyDescriptor.getName(), propertyDescriptor);
                    beanInfoMap.put(className, propertyDescriptorMap);
                }
            } catch (IntrospectionException ex) {
                throw new BeanReflectionException(ex.getMessage(), ex);
            }
        }

        private void processRow() throws SQLException {
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
