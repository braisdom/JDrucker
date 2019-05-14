package org.braisdom.spring.sample.database;

import org.braisdom.drucker.annotation.Table;
import org.braisdom.drucker.database.AbstractTable;

@Table(file = "/xsql/user.xsql", entityBeanClass = UserTable.User.class)
public interface UserTable extends AbstractTable<UserTable> {

    class User {
        private String name;
        private String gender;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
