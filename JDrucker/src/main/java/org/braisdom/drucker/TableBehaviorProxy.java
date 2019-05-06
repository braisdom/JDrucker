package org.braisdom.drucker;

import javax.sql.DataSource;

public class TableBehaviorProxy {

    private final DataSource dataSource;
    private final String tableName;

    public TableBehaviorProxy(DataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.tableName = tableName;
    }

}
