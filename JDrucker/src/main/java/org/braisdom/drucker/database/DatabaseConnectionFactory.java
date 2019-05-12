package org.braisdom.drucker.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnectionFactory {

    Connection getConnection() throws SQLException;

}
