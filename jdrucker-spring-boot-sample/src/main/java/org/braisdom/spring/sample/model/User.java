package org.braisdom.spring.sample.model;

import org.braisdom.drucker.ActiveRecord;
import org.braisdom.drucker.annotation.SQL;
import org.braisdom.drucker.annotation.Table;
import org.braisdom.drucker.database.SQLExecutionType;
import org.braisdom.spring.sample.controller.SampleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Table(file = "xsql/user.xsql", tableName = "users")
public abstract class User extends ActiveRecord<User> {

    private String test = "Hello World";

    @SQL(id = "test", executionType = SQLExecutionType.SELECT_ONE)
    public abstract User findUser();

    @Transactional
    public void helloWorld() {

    }

}
