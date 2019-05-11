package org.braisdom.spring.sample;

import org.braisdom.drucker.TableDiscoverer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@TableDiscoverer(classpath = {"org.braisdom.spring.sample.table"})
public class SampleApplication {

    public static final void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

}
