package org.braisdom.drucker.database;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RawEntity {

    private Map<String, Object> attributes = new HashMap<>();

    final void setAttributes(Map<String, Object> attributes) {
        Objects.requireNonNull(attributes, "attributes cannot be null");
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
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

    public byte[] getBytes(String columnName) {
        return (byte[]) attributes.get(columnName);
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
