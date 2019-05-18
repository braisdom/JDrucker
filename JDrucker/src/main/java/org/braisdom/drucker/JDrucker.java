package org.braisdom.drucker;

import net.sf.cglib.proxy.Enhancer;
import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.database.TableBehaviorProxy;

import java.util.Objects;

public class JDrucker {

    public static Object getProxy(Class tableClass,
                                  DatabaseSession databaseSession) {
        Objects.requireNonNull(databaseSession, "tableClass cannot be null");
        Objects.requireNonNull(tableClass, "databaseSession cannot be null");

        TableBehaviorProxy tableBehaviorProxy = new TableBehaviorProxy(databaseSession, tableClass);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tableClass);
        enhancer.setCallback(tableBehaviorProxy);

        return enhancer.create();
    }

}
