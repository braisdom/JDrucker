package org.braisdom.drucker.database;

import static org.mockito.Mockito.*;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultDatabaseSessionTest {

    @Test
    public void testExecuteQuery() throws SQLException {
        DatabaseConnectionFactory databaseConnectionFactory = mock(DatabaseConnectionFactory.class);
        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);

        when(connection.createStatement()).thenReturn(statement);
        when(databaseConnectionFactory.getConnection()).thenReturn(connection);
    }
}
