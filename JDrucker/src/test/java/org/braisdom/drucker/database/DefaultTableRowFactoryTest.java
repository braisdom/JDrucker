package org.braisdom.drucker.database;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultTableRowFactoryTest extends AbstractTest {

    @Test
    public void createTableRowTest() throws SQLException, BeanReflectionException {
        DefaultTableRowFactory defaultTableRowFactory = new DefaultTableRowFactory();

        TableMetaData tableMetaData = mockTableMetaData();
        ResultSet resultSet = mockResultSet();

        DemoTableTest demoTable = (DemoTableTest) defaultTableRowFactory.createTableRow(DemoTableTest.class,
                tableMetaData, resultSet);

        Assert.assertFalse(demoTable.getAttributes().isEmpty());
        // Validate the raw attribute method
        Assert.assertTrue(demoTable.getString("name").equals("Wangyonghe"));
        Assert.assertTrue(demoTable.getInteger("age") == 32);
        Assert.assertTrue(demoTable.getDate("birthday").equals(new Date(2019, 05, 31)));
        // Validate the bean properties
        Assert.assertTrue(demoTable.getName().equals("Wangyonghe"));
        Assert.assertTrue(demoTable.getAge() == 32);
        Assert.assertTrue(demoTable.getBirthday().equals(new Date(2019, 05, 31)));
    }
}
