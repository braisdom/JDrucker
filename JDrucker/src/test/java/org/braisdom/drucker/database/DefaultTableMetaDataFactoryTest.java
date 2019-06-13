package org.braisdom.drucker.database;

import org.junit.Assert;
import org.junit.Test;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultTableMetaDataFactoryTest extends AbstractTest {

    @Test
    public void testCreateTableMetaData() throws SQLException {
        DatabaseMetaData databaseMetaData = mock(DatabaseMetaData.class);
        ResultSetMetaData resultSetMetaData = mock(ResultSetMetaData.class);

        when(resultSetMetaData.getColumnCount()).thenReturn(3);
        when(resultSetMetaData.getColumnName(1)).thenReturn("name");
        when(resultSetMetaData.getColumnName(2)).thenReturn("age");
        when(resultSetMetaData.getColumnName(3)).thenReturn("birthday");

        DefaultTableMetaDataFactory defaultTableMetaDataFactory = new DefaultTableMetaDataFactory();
        TableMetaData tableMetaData = defaultTableMetaDataFactory.createTableMetaData(DemoTableTest.class,
                databaseMetaData, resultSetMetaData);

        Assert.assertTrue(tableMetaData.getColumnNames().length == 3);
        Assert.assertTrue(tableMetaData.getColumnMetaData("name") != null);
    }

}
