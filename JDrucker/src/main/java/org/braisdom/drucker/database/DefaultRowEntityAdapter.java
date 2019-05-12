package org.braisdom.drucker.database;

import java.sql.ResultSet;

public class DefaultRowEntityAdapter implements RowEntityAdapter {

    private final Class entityBeanClass;
    private final ResultSet resultSet;

    public DefaultRowEntityAdapter(Class entityBeanClass, ResultSet resultSet) {
        this.entityBeanClass = entityBeanClass;
        this.resultSet = resultSet;
    }

    @Override
    public Object getEntity() {
        return null;
    }

    @Override
    public RowEntityAdapter getRawEntity() {
        return null;
    }
}
