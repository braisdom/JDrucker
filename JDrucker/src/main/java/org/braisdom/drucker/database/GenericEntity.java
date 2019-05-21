package org.braisdom.drucker.database;

import org.braisdom.drucker.database.TableMetaData.ColumnMetaData;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenericEntity {

    private Map<String, Object> attributes = new HashMap<>();
    private TableMetaData tableMetaData;

    final void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    final void setTableMetaData(TableMetaData tableMetaData) {
        this.tableMetaData = tableMetaData;
    }

    public TableMetaData getTableMetaData() {
        return tableMetaData;
    }

    public ColumnMetaData getColumnMetaData(String columnName) {
        return tableMetaData.getColumnMetaData(columnName);
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public String getString(String columnName) {
        return (String) attributes.get(columnName);
    }

    public Integer getInteger(String columnName) {
        return (Integer) attributes.get(columnName);
    }

    public Float getFloat(String columnName) {
        return (Float) attributes.get(columnName);
    }

    public Double getDouble(String columnName) {
        return (Double) attributes.get(columnName);
    }

    public Byte[] getBytes(String columnName) {
        return (Byte[]) attributes.get(columnName);
    }

    public Boolean getBoolean(String columnName) {
        return (Boolean) attributes.get(columnName);
    }

    public BigDecimal getBigDecimal(String columnName) {
        return (BigDecimal) attributes.get(columnName);
    }

    public Short getShort(String columnName) {
        return (Short) attributes.get(columnName);
    }

    public Date getDate(String columnName) {
        return (Date) attributes.get(columnName);
    }

    public Time getTime(String columnName) {
        return (Time) attributes.get(columnName);
    }

    public Timestamp getTimestamp(String columnName) {
        return (Timestamp) attributes.get(columnName);
    }

}
