package org.braisdom.drucker;

import freemarker.template.TemplateException;
import org.braisdom.drucker.xsql.XSQLParser;
import org.braisdom.drucker.xsql.XSQLParsingException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XSqlParserTest {

    public static void main(String[] args) throws IOException, TemplateException, XSQLParsingException {
        Map<String, String> user = new HashMap<>();
        Map<String, Object> dataModel = new HashMap<>();
        user.put("gender", "男");
        dataModel.put("user", user);

        System.out.println(XSQLParser.parse("/sql/user.xsql", "query_all_users1", XSqlParserTest.class, dataModel));
    }
}
