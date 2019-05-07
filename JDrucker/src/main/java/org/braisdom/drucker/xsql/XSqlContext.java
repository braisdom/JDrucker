package org.braisdom.drucker.xsql;

import java.util.HashMap;

public class XSqlContext extends HashMap<String, Object> {

    public XSqlContext addAttribute(String name, Object value) {
        put(name, value);
        return this;
    }
}
