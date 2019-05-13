package org.braisdom.drucker.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowEntityAdapterFactory {

    public RowEntityAdapter createRowEntityAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException;
}
