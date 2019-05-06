package org.braisdom.drucker;

public class JDruckerTest {

    public static void main(String[] args) {
        UserTable userTable = JDrucker.getProxy(UserTable.class, null);
//
        userTable.findAll();
    }
}
