package org.braisdom.drucker.database;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public interface TableMetaDataFactory {

    public TableMetaData getTableDescriptor(Class<? extends AbstractTable> tableClass,
                                            ResultSetMetaData resultSetMetaData) throws SQLException;

}
