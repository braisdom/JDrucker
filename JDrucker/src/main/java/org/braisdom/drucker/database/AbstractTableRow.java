package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Proxied;
import org.braisdom.drucker.database.TableMetaData.ColumnMetaData;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

public abstract class AbstractTableRow implements TableRow {

    @Proxied
    public abstract Map<String, Object> getAttributes();

    @Proxied
    public abstract ColumnMetaData getColumnMetaData(String columnName);

    public String getString(String columnName) {
        return (String) getAttributes().get(columnName);
    }

    public Integer getInteger(String columnName) {
        return (Integer) getAttributes().get(columnName);
    }

    public Float getFloat(String columnName) {
        return (Float) getAttributes().get(columnName);
    }

    public Double getDouble(String columnName) {
        return (Double) getAttributes().get(columnName);
    }

    public Byte[] getBytes(String columnName) {
        return (Byte[]) getAttributes().get(columnName);
    }

    public Boolean getBoolean(String columnName) {
        return (Boolean) getAttributes().get(columnName);
    }

    public BigDecimal getBigDecimal(String columnName) {
        return (BigDecimal) getAttributes().get(columnName);
    }

    public Short getShort(String columnName) {
        return (Short) getAttributes().get(columnName);
    }

    public Date getDate(String columnName) {
        return (Date) getAttributes().get(columnName);
    }

    public Time getTime(String columnName) {
        return (Time) getAttributes().get(columnName);
    }

    public Timestamp getTimestamp(String columnName) {
        return (Timestamp) getAttributes().get(columnName);
    }

}
