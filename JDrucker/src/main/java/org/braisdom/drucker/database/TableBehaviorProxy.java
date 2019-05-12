package org.braisdom.drucker.database;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TableBehaviorProxy implements InvocationHandler {

    private final DatabaseSession databaseSession;

    public TableBehaviorProxy(DatabaseSession databaseSession) {
        this.databaseSession = databaseSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<? extends AbstractTable> declaringClass = (Class<? extends AbstractTable>) method.getDeclaringClass();
        TableDescriptor tableDescriptor = new TableDescriptor(declaringClass);
        databaseSession.executeQuery("insert into users(name, gender) values ('wangyonghe', 'male')");
        if(AbstractTable.class.equals(declaringClass)) {

        }
        return null;
    }

}
