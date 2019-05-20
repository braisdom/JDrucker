package org.braisdom.drucker;

import org.braisdom.drucker.database.AbstractTable;
import org.braisdom.drucker.database.RawEntity;

public abstract class ActiveRecord<T> extends RawEntity implements AbstractTable<T> {

}
