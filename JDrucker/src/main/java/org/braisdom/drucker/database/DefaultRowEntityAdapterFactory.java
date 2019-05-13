package org.braisdom.drucker.database;

import java.sql.ResultSet;

public class DefaultRowEntityAdapterFactory implements RowEntityAdapterFactory {

    @Override
    public RowEntityAdapter createRowEntityAdapter(TableMetaData tableMetaData, ResultSet resultSet) {
        return new DefaultRowEntityAdapter(tableMetaData, resultSet);
    }

}
