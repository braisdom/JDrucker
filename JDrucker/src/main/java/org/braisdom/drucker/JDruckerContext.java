package org.braisdom.drucker;

import org.braisdom.drucker.database.TableDescriptor;

import javax.sql.DataSource;

public interface JDruckerContext {

    public DataSource getDataSource();

    public TableDescriptor getTableDescriptor();

}
