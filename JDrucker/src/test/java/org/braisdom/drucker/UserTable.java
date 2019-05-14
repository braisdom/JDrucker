package org.braisdom.drucker;

import org.braisdom.drucker.annotation.Table;
import org.braisdom.drucker.database.AbstractTable;

@Table(file = "/sql/user.xsql", entityBeanClass = UserTable.User.class)
public interface UserTable<T> extends AbstractTable<T> {

    public static class User {
        private String name;
        private Integer gender;
        private String address;
        private String occupation;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }
    }
}
