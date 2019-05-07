package org.braisdom.drucker;

import javax.sql.DataSource;

public interface JDruckerContext {

    public DataSource getDataSource();

    public TableDescriptor getTableDescriptor();

}
