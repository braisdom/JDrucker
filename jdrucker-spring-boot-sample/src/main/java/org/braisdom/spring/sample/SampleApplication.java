package org.braisdom.spring.sample;

import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.spring.MigrationRunner;
import org.braisdom.drucker.spring.TableDiscoverer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
@TableDiscoverer(classpath = {"org.braisdom.spring.sample.model"})
public class SampleApplication {

    @Bean
    public CommandLineRunner migrationRunner(ApplicationContext applicationContext, DatabaseSession databaseSession) {
        return new MigrationRunner(applicationContext, databaseSession);
    }

    public static final void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

}
