package com.easygoapp.config;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Stanislav Markov mailto: stasmarkov88@gmail.com
 */

@EnableJpaRepositories("com.easygoapp.repository")
@PropertySource({"classpath:db-hsql.properties"})
public class IntegrationTestPersistenceConfig extends PersistenceConfig {

    @Autowired
    private Environment environment;

    @Override
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(environment.getProperty("jdbc.hsql.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(environment.getProperty("jdbc.hsql.url")));
        dataSource.setUsername(Preconditions.checkNotNull(environment.getProperty("jdbc.hsql.user")));
        dataSource.setPassword(Preconditions.checkNotNull(environment.getProperty("jdbc.hsql.pass")));
        return dataSource;
    }

    @Override
    protected Properties getHibernateProperties() throws IOException {
        PropertiesFactoryBean pfb = new PropertiesFactoryBean();
        pfb.setLocation(new ClassPathResource("hibernateHsql.properties"));
        pfb.afterPropertiesSet();
        return pfb.getObject();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}