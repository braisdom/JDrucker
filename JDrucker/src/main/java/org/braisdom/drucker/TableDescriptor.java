package org.braisdom.drucker;

import org.braisdom.drucker.annotation.Table;

public final class TableDescriptor {

    private final Table tableAnnotation;
    private final Class tableClass;

    public TableDescriptor(Class tableClass, Table tableAnnotation) {
        this.tableClass = tableClass;
        this.tableAnnotation = tableAnnotation;
    }

    public String getTableName() {
        String rawTableName = tableAnnotation.name();
        if(rawTableName == null || rawTableName.length() == 0)
            return WordUtil.tableize(tableAnnotation.model().getSimpleName());
        return rawTableName;
    }

    public Class getModelClass() {
        Class rawClass = tableAnnotation.model();
        if(rawClass == null)
            return RawModel.class;
        return rawClass;
    }
}
