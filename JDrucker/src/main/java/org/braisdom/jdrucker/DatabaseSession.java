package org.braisdom.jdrucker;

import java.io.Closeable;

public interface DatabaseSession extends Closeable {

    public void openTransaction();

    public void commit();

    public void rollback();

    public Object query(String sql);

    public int update(String sql);

}
