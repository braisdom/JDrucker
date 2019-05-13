package org.braisdom.drucker.database;

import java.sql.ResultSet;

public class DefaultRowEntityAdapter implements RowEntityAdapter {

    private final TableMetaData tableMetaData;
    private final ResultSet resultSet;

    public DefaultRowEntityAdapter(TableMetaData tableMetaData, ResultSet resultSet) {
        this.tableMetaData = tableMetaData;
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
