package org.braisdom.drucker.database;

import java.sql.ResultSet;

public interface RowConverter<M> {

    public M convert(ResultSet rowSet);

}
