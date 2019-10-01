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

        int c = 1;// ...0001=2^0
        int r = 2;// ...0010=2^1
        int u = 4;// ...0100=2^3
        int d = 8;// ...1000=2^4

        int userb = c | d | r;

        System.out.println(userb);

        System.out.println(userb == (c | userb));
    }

}
