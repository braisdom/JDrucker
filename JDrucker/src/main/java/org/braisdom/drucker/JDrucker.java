package org.braisdom.drucker;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDrucker {

    private static class DefaultInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

    public static <T extends TableBehavior> T getProxy(Class<T> tableClass, DataSource dataSource) {
        DefaultInvocationHandler invocationHandler = new DefaultInvocationHandler();
        Object object = Proxy.newProxyInstance(
                tableClass.getClassLoader(), new Class<?>[]{tableClass}, invocationHandler);
        return tableClass.cast(object);
    }
}
