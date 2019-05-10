package org.braisdom.drucker.database;

import java.sql.ResultSet;
import java.sql.Statement;

public interface DatabaseSession {

    public Statement executeQuery(String sql);

    public int executeUpdate(String sql);

}
