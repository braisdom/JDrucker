package org.braisdom.spring.sample.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class SampleController {

    @RequestMapping("/")
    public String sayHello() {
        return "Hello";
    }

    @Transactional
    @RequestMapping("/say_hello")
    public String sayHello1() {
        return "Hello";
    }
}
