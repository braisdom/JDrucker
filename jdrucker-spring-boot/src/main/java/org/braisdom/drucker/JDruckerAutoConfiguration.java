package org.braisdom.drucker;

import org.braisdom.drucker.database.DatabaseSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class JDruckerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DatabaseSessionFactory databaseSessionFactory(DataSource dataSource) {
        return null;
    }
}
