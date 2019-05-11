package org.braisdom.drucker;

import org.braisdom.drucker.database.AbstractTable;
import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.database.TableBehaviorProxy;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;

public class JDrucker {

    public static <T extends AbstractTable> T getProxy(Class<T> tableBehaviorClass, DatabaseSession databaseSession) {
        TableBehaviorProxy invocationHandler = new TableBehaviorProxy(databaseSession);
        Object object = Proxy.newProxyInstance(
                tableBehaviorClass.getClassLoader(), new Class<?>[]{tableBehaviorClass}, invocationHandler);
        return tableBehaviorClass.cast(object);
    }
}
