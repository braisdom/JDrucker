package org.braisdom.drucker;

import org.braisdom.drucker.database.*;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class JDruckerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(DataSource.class)
    public DatabaseSession databaseSession(DataSource dataSource) {
        return new DefaultDatabaseSession(new DefaultDatabaseConnectionFactory(dataSource),
                new DefaultTableMetaDataFactory(), new DefaultRowEntityAdapterFactory());
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
