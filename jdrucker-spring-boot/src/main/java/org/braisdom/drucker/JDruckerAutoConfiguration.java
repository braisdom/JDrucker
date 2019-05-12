package org.braisdom.drucker;

import org.braisdom.drucker.database.DatabaseConnectionFactory;
import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.database.DefaultDatabaseSession;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class JDruckerAutoConfiguration {

    @Bean
    @ConditionalOnBean(DataSource.class)
    @ConditionalOnMissingBean
    public DatabaseSession databaseSession(DataSource dataSource) {
        return new DefaultDatabaseSession(new DefaultDatabaseConnectionFactory(dataSource));
    }

    private class DefaultDatabaseConnectionFactory implements DatabaseConnectionFactory {

        private final DataSource dataSource;

        public DefaultDatabaseConnectionFactory(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public Connection getConnection() throws SQLException {
            return DataSourceUtils.getConnection(dataSource);
        }
    }

}
