package org.braisdom.drucker.database;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

public interface TableRow {

    Map<String, Object> getAttributes();

    String getString(String columnName);

    Integer getInteger(String columnName);

    Float getFloat(String columnName);

    Double getDouble(String columnName);

    Byte[] getBytes(String columnName);

    Boolean getBoolean(String columnName);

    BigDecimal getBigDecimal(String columnName);

    Short getShort(String columnName);

    Date getDate(String columnName);

    Time getTime(String columnName);

    Timestamp getTimestamp(String columnName);
}
