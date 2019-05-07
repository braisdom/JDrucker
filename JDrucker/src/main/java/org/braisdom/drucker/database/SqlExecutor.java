package org.braisdom.drucker.database;

import java.util.List;

public interface SqlExecutor {

    Object query(String sql);

    List queryList(String sql);

    int update(String sql);
}
