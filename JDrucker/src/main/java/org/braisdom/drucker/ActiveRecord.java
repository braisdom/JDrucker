package org.braisdom.drucker;

import org.braisdom.drucker.annotation.TableColumn;
import org.braisdom.drucker.database.AbstractTableRow;
import org.braisdom.drucker.database.TableBehavior;

public abstract class ActiveRecord<T> extends AbstractTableRow implements TableBehavior<T> {

    @TableColumn(value = "id", updatable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
