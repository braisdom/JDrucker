package org.braisdom.drucker;

import org.braisdom.drucker.xsql.XSQLDefinition;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JDruckerTest {

    @Test
    public void testLoadXsqlFile() throws IOException {
        String xsqlPath = "xsql";
        String xsqlFileName = "xsql/users.xsql";
        String xsqlFileName1 = "xsql/admin/users.xsql";
        String xsqlFileName2 = "xsql/management/users.xsql";
        String xsqlFileName3 = "xsql/management/admin/users.xsql";

        JDrucker.loadXsqlFile(xsqlPath);

        XSQLDefinition.XSQLDeclaration xsqlDeclaration = JDrucker.getXSQLDeclaration(xsqlFileName);
        XSQLDefinition.XSQLDeclaration xsqlDeclaration1 = JDrucker.getXSQLDeclaration(xsqlFileName1);
        XSQLDefinition.XSQLDeclaration xsqlDeclaration2 = JDrucker.getXSQLDeclaration(xsqlFileName2);
        XSQLDefinition.XSQLDeclaration xsqlDeclaration3 = JDrucker.getXSQLDeclaration(xsqlFileName3);

        Assert.assertNotNull(xsqlDeclaration);
        Assert.assertNotNull(xsqlDeclaration1);
        Assert.assertNotNull(xsqlDeclaration2);
        Assert.assertNotNull(xsqlDeclaration3);
    }

}
