package org.braisdom.drucker.database;

public interface EntityAdapter {

    public Object getEntity() throws EntityInstantiateException;

    public RawEntity getRaw();

}
