package org.braisdom.drucker.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TableRowFactory {

    TableRow createTableRow(Class<? extends TableRow> tableRowClass,
                            TableMetaData tableMetaData,
                            ResultSet resultSet) throws SQLException, BeanReflectionException;
}