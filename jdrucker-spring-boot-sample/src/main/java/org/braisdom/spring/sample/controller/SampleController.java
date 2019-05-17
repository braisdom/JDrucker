package org.braisdom.spring.sample.controller;

import org.braisdom.spring.sample.database.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@Transactional
@RestController
@RequestMapping("/")
public class SampleController {

    @Autowired
    private UserTable userTable;

    @RequestMapping("/")
    public String sayHello() {
        userTable.findAll(10);
        return "Hello";
    }

    @RequestMapping("/say_hello")
    public String sayHello1() throws ServletException {
        userTable.findById(1);
        return null;
    }
}
