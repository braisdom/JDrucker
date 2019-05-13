package org.braisdom.drucker.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultRowEntityAdapterFactory implements RowEntityAdapterFactory {

    @Override
    public RowEntityAdapter createRowEntityAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException {
        return new DefaultRowEntityAdapter(tableMetaData, resultSet);
    }

}
