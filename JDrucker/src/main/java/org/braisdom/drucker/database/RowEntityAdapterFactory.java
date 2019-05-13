package org.braisdom.drucker.database;

import java.sql.ResultSet;

public interface RowEntityAdapterFactory {

    public RowEntityAdapter createRowEntityAdapter(TableMetaData tableMetaData, ResultSet resultSet);
}
