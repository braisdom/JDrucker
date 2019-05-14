package org.braisdom.drucker;

import org.braisdom.drucker.database.AbstractTable;
import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.database.TableBehaviorProxy;
import org.braisdom.drucker.database.TableMetaDataFactory;

import java.lang.reflect.Proxy;
import java.util.Objects;

public class JDrucker {

    public static <T extends AbstractTable> T getProxy(Class<T> tableClass,
                                                       DatabaseSession databaseSession) {
        Objects.requireNonNull(databaseSession, "tableClass cannot be null");
        Objects.requireNonNull(tableClass, "databaseSession cannot be null");

        TableBehaviorProxy tableBehaviorProxy = new TableBehaviorProxy(databaseSession, tableClass);
        Object object = Proxy.newProxyInstance(
                tableClass.getClassLoader(), new Class<?>[]{tableClass}, tableBehaviorProxy);

        return tableClass.cast(object);
    }

}
