package org.braisdom.spring.sample.table;

import org.braisdom.drucker.annotation.TableBehavior;

@TableBehavior(beanClass = UserTable.User.class)
public interface UserTable extends org.braisdom.drucker.database.TableBehavior {

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
