package org.braisdom.drucker.database;

import org.braisdom.drucker.annotation.SQLParam;

public interface SQLParameter {

    SQLParam getParam();

    Object getValue();
}
