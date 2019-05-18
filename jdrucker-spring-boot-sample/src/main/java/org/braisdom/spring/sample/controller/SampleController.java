package org.braisdom.spring.sample.controller;

import org.braisdom.spring.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
@RequestMapping("/")
public class SampleController {

    @Autowired
    private User user;

    @RequestMapping("/")
    public String sayHello() {
        return "Hello";
    }

    @RequestMapping("/say_hello")
    public String sayHello1() throws ServletException {
        user.helloWorld();
        return null;
    }
}
