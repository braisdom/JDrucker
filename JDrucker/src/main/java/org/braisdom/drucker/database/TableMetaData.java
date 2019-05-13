package org.braisdom.drucker.database;

public interface TableMetaData {

    String getName();

    String[] getColumnNames();

    ColumnMetaData getColumnMetaData(String columnName);

    Class<? extends AbstractTable> getEntityBeanClass();

    class ColumnMetaData {
        private final String name;
        private final Integer type;
        private final Integer index;
        private final Integer scale;
        private final Integer notnull;

        public ColumnMetaData(String name, Integer index, Integer type,
                              Integer scale, Integer notnull) {
            this.name = name;
            this.type = type;
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

        public Integer getIndex() {
            return index;
        }

        public Integer getScale() {
            return scale;
        }

        public Integer isColumnNotnull() {
            return notnull;
        }
    }
}
