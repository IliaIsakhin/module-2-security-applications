package com.epam.edu.module2securityapplications.configuration;

import com.epam.edu.module2securityapplications.service.UserInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ServiceConfiguration {

    public ServiceConfiguration(UserInitializer userInitializer) {
        this.userInitializer = userInitializer;
    }
    
    private final UserInitializer userInitializer;
    
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager service = new JdbcUserDetailsManager();
        service.setDataSource(dataSource);

        try {
            userInitializer.initTestUsers().forEach(service::createUser);
        } catch (DuplicateKeyException ignored) {
            // for testing purposes
        }
        return service;
    }

}
