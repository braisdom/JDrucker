package org.braisdom.drucker;

import org.braisdom.drucker.database.DefaultSqlExecutor;
import org.braisdom.drucker.database.SqlExecutor;
import org.braisdom.drucker.database.TableBehavior;
import org.braisdom.drucker.database.TableDescriptor;
import org.braisdom.drucker.xsql.XSqlContext;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDrucker {

    private static class DefaultInvocationHandler implements InvocationHandler {

        private final SqlExecutor sqlExecutor;

        public DefaultInvocationHandler(SqlExecutor sqlExecutor) {
            this.sqlExecutor = sqlExecutor;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<? extends TableBehavior> declaringClass = (Class<? extends TableBehavior>) method.getDeclaringClass();
            XSqlContext xSqlContext = new XSqlContext();

            if(TableBehavior.class.equals(declaringClass)) {

            }

            TableDescriptor tableDescriptor = new TableDescriptor(declaringClass);
            return null;
        }
    }

    public static <T extends TableBehavior> T getProxy(Class<T> tableBehaviorClass, DataSource dataSource) {
        DefaultInvocationHandler invocationHandler = new DefaultInvocationHandler(new DefaultSqlExecutor(dataSource));
        Object object = Proxy.newProxyInstance(
                tableBehaviorClass.getClassLoader(), new Class<?>[]{tableBehaviorClass}, invocationHandler);
        return tableBehaviorClass.cast(object);
    }
}
