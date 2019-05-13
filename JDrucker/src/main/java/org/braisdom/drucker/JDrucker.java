package org.braisdom.drucker;

import org.braisdom.drucker.database.AbstractTable;
import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.database.TableBehaviorProxy;
import org.braisdom.drucker.database.TableMetaDataFactory;

import java.lang.reflect.Proxy;

public class JDrucker {

    public static <T extends AbstractTable> T getProxy(Class<T> tableClass,
                                                       DatabaseSession databaseSession) {
        TableBehaviorProxy tableBehaviorProxy = new TableBehaviorProxy(databaseSession);
        Object object = Proxy.newProxyInstance(
                tableClass.getClassLoader(), new Class<?>[]{tableClass}, tableBehaviorProxy);
        return tableClass.cast(object);
    }

}
