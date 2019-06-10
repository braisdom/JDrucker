package org.braisdom.drucker.spring;

import org.braisdom.drucker.ActiveRecord;
import org.braisdom.drucker.JDrucker;
import org.braisdom.drucker.database.DatabaseSession;
import org.springframework.beans.factory.FactoryBean;

public class TableFactoryBean implements FactoryBean {

    private final Class tableClass;
    private final DatabaseSession databaseSession;

    public TableFactoryBean(Class<? extends ActiveRecord> tableClass, DatabaseSession databaseSession) {
        this.tableClass = tableClass;
        this.databaseSession = databaseSession;
    }

    @Override
    public Object getObject() throws Exception {
        return JDrucker.getProxy(databaseSession, tableClass, tableClass);
    }

    @Override
    public Class<?> getObjectType() {
        return tableClass;
    }
}
