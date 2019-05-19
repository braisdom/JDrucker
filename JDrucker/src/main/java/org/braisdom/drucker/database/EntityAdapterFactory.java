package org.braisdom.drucker.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityAdapterFactory {

    EntityAdapter createEntityAdapter(TableMetaData tableMetaData, ResultSet resultSet) throws SQLException;
}
