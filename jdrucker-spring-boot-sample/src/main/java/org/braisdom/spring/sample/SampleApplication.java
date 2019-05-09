package org.braisdom.spring.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class SampleApplication {

    public static final void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

}
