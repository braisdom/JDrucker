package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.Table;
import org.braisdom.drucker.annotation.TableRowBean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractTest {

    protected TableMetaData mockTableMetaData() {
        TableMetaData tableMetaData = mock(TableMetaData.class);

        when(tableMetaData.getColumnNames()).thenReturn(new String[]{"name", "age", "birthday"});
        when(tableMetaData.getColumnMetaData("name"))
                .thenReturn(new TableMetaData.ColumnMetaData("name", 0, Types.VARCHAR, "VARCHAR", 0, 0));
        when(tableMetaData.getColumnMetaData("age"))
                .thenReturn(new TableMetaData.ColumnMetaData("age", 1, Types.INTEGER, "INTEGER", 0, 0));
        when(tableMetaData.getColumnMetaData("birthday"))
                .thenReturn(new TableMetaData.ColumnMetaData("birthday", 2, Types.DATE, "DATE", 0, 0));

        return tableMetaData;
    }

    protected ResultSet mockResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getString(0)).thenReturn("Wangyonghe");
        when(resultSet.getInt(1)).thenReturn(32);
        when(resultSet.getDate(2)).thenReturn(new Date(2019, 05, 31));

        return resultSet;
    }

    @Table(file = "xsql/demos.xsql")
    @TableRowBean
    public static abstract class DemoTableTest extends AbstractTableRow {
        private String name;
        private Integer age;
        private Date birthday;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }
}
