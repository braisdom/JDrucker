package org.braisdom.drucker;

import org.braisdom.drucker.database.AbstractTable;
import org.braisdom.drucker.database.RawEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class ActiveRecord<T> extends RawEntity implements AbstractTable<T> {

    private Map<String, Object> seldomAttributes = new HashMap<>();

    public final void setSeldomAttributes(Map<String, Object> seldomAttributes) {
        Objects.requireNonNull(seldomAttributes, "seldomAttributes cannot be null");
        this.seldomAttributes = seldomAttributes;
    }

    public Map<String, Object> getSeldomAttributes() {
        return seldomAttributes;
    }
}
