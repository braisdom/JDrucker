package org.braisdom.drucker.database;

public interface TableMetaDataProvider {

    TableMetaData getTableMetaData();

    TableMetaData.ColumnMetaData getColumnMetaData(String columnName);

}
