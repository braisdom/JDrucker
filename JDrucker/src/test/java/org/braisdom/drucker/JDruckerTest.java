package org.braisdom.drucker;

public class JDruckerTest {

    public static void main(String[] args) {
        UserTable<UserTable.User> userTable = JDrucker.getProxy(UserTable.class, null);
        UserTable.User users = userTable.findById(1);
    }
}
