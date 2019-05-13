package org.braisdom.spring.sample;

import org.braisdom.drucker.TableDiscoverer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@TableDiscoverer(classpath = {"org.braisdom.spring.sample.database"})
public class SampleApplication implements TransactionManagementConfigurer {

    @Resource(name="transactionManager")
    private PlatformTransactionManager transactionManager;

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager;
    }

    public static final void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

}
