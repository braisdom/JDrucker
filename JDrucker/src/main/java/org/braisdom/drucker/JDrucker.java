package org.braisdom.drucker;

import org.braisdom.drucker.annotation.Table;
import org.braisdom.drucker.database.TableBehavior;
import org.braisdom.drucker.database.TableBehaviorProxy;
import org.braisdom.drucker.database.TableDescriptor;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDrucker {

    private static class DefaultInvocationHandler implements InvocationHandler {

        private final JDruckerContext context;
        private final TableBehaviorProxy tableBehaviorProxy;

        public DefaultInvocationHandler(JDruckerContext context) {
            this.context = context;
            this.tableBehaviorProxy = new TableBehaviorProxy(context.getDataSource(), context.getTableDescriptor());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            return null;
        }
    }

    private static class JDruckerContextImpl implements JDruckerContext {

        private final TableDescriptor tableDescriptor;
        private final DataSource dataSource;

        public JDruckerContextImpl(TableDescriptor tableDescriptor, DataSource dataSource) {
            this.tableDescriptor = tableDescriptor;
            this.dataSource = dataSource;
        }

        @Override
        public TableDescriptor getTableDescriptor() {
            return tableDescriptor;
        }

        @Override
        public DataSource getDataSource() {
            return dataSource;
        }
    }

    public static <T extends TableBehavior> T getProxy(Class<T> tableClass, DataSource dataSource) {
        Table tableAnnotation = tableClass.getAnnotation(Table.class);
        DefaultInvocationHandler invocationHandler = new DefaultInvocationHandler(
                new JDruckerContextImpl(new TableDescriptor(tableClass, tableAnnotation), dataSource));
        Object object = Proxy.newProxyInstance(
                tableClass.getClassLoader(), new Class<?>[]{tableClass}, invocationHandler);
        return tableClass.cast(object);
    }
}
