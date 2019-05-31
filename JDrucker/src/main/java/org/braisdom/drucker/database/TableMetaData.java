package org.braisdom.drucker.database;

import java.sql.SQLException;

public interface TableMetaData {

    String getDatabaseProductName() throws SQLException;

    String[] getColumnNames();

    ColumnMetaData getColumnMetaData(String columnName);

    class ColumnMetaData {
        private final String name;
        private final Integer type;
        private final Integer index;
        private final Integer scale;
        private final Integer notnull;
        private final String typeName;

        public ColumnMetaData(String name, Integer index, Integer type, String typeName,
                              Integer scale, Integer notnull) {
            this.name = name;
            this.type = type;
            this.typeName = typeName;
            this.index = index;
            this.scale = scale;
            this.notnull = notnull;
        }

        public String getName() {
            return name;
        }

        public Integer getType() {
            return type;
        }

        public String getTypeName() {
            return typeName;
        }

        public Integer getIndex() {
            return index;
        }

        public Integer getScale() {
            return scale;
        }

        public Integer isNotnull() {
            return notnull;
        }
    }
}
