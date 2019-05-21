package org.braisdom.drucker;

import org.braisdom.drucker.annotation.TableColumn;
import org.braisdom.drucker.database.AbstractTable;
import org.braisdom.drucker.database.GenericEntity;

public abstract class ActiveRecord<T> extends GenericEntity implements AbstractTable<T> {

    @TableColumn(value = "id", updatable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
