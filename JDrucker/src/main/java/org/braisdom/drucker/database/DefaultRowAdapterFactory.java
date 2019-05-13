package org.braisdom.drucker.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultRowAdapterFactory implements RowAdapterFactory {

    @Override
    public RowAdapter createRowAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException {
        return new DefaultRowAdapter(tableMetaData, resultSet);
    }

}
