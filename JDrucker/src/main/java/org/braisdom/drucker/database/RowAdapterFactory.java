package org.braisdom.drucker.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowAdapterFactory {

    EntityAdapter createRowAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException;
}
