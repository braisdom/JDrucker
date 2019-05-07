package org.braisdom.drucker.database;

public interface TableMetaData {

    public String[] getFields();

    public String getFieldType(String field);

    public Integer getFieldLength(String filed);

}
