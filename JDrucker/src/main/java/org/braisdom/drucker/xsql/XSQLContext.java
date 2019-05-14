package org.braisdom.drucker.xsql;

import java.util.HashMap;
import java.util.Map;

public class XSQLContext {

    private static Map<String, XSQLFunction> functionMap = new HashMap<>();

    private Map<String, Object> attributes;

    public XSQLContext() {
        this.attributes = new HashMap<>();
    }

    public XSQLContext addAttribute(String name, Object value) {
        attributes.put(name, value);
        return this;
    }

    public Map<String, Object> toFreemarkContext() {
        attributes.putAll(functionMap);
        return attributes;
    }
}
