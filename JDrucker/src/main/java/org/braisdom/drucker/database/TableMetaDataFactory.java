package org.braisdom.drucker.database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public interface TableMetaDataFactory {

    public TableMetaData createTableMetaData(Class<?> tableClass,
                                             DatabaseMetaData databaseMetaData,
                                             ResultSetMetaData resultSetMetaData) throws SQLException;

}
