package org.braisdom.drucker;

import org.braisdom.drucker.database.DatabaseSession;
import org.springframework.beans.factory.FactoryBean;

public class TableFactoryBean implements FactoryBean {

    private final Class tableClass;
    private final DatabaseSession databaseSession;

    public TableFactoryBean(Class tableClass, DatabaseSession databaseSession) {
        this.tableClass = tableClass;
        this.databaseSession = databaseSession;
    }

    @Override
    public Object getObject() throws Exception {
        return JDrucker.getProxy(tableClass, databaseSession);
    }

    @Override
    public Class<?> getObjectType() {
        return tableClass;
    }
}
