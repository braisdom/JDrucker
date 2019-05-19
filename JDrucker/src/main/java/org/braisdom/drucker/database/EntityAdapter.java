package org.braisdom.drucker.database;

import java.util.Map;

public interface EntityAdapter {

    public Object getEntity() throws EntityInstantiateException;

    public Map<String, Object> getRaw();

}
