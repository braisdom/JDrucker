package org.braisdom.drucker.spring;

import org.braisdom.drucker.database.*;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class JDruckerAutoConfiguration implements ApplicationContextAware {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(DataSource.class)
    public DatabaseSession databaseSession(DataSource dataSource) {
        return new DefaultDatabaseSession(new DefaultDatabaseConnectionFactory(dataSource),
                new DefaultTableMetaDataFactory(), new DefaultTableRowFactory());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.setApplicationContext(applicationContext);
    }

    protected static class DefaultDatabaseConnectionFactory implements DatabaseConnectionFactory {

        private final DataSource dataSource;

        public DefaultDatabaseConnectionFactory(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public Connection getConnection() throws SQLException {
            return new ConnectionWrapper(dataSource, DataSourceUtils.getConnection(dataSource));
        }
    }

}
