package org.braisdom.drucker;

import java.util.List;

public class JDruckerTest {

    public static void main(String[] args) {
        UserTable userTable = JDrucker.getProxy(UserTable.class, null);
        UserTable.User users = userTable.findById(1);
    }
}
