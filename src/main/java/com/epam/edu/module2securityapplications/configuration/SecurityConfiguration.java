package com.epam.edu.module2securityapplications.configuration;

import com.epam.edu.module2securityapplications.filter.BruteForceProtectionServletFilter;
import com.epam.edu.module2securityapplications.service.BruteForceProtectionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private BruteForceProtectionCheckService bruteForceProtectionCheckService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/info").hasAuthority(Permissions.VIEW_INFO.getAuthority())
                .antMatchers("/api/admin").hasAuthority(Permissions.VIEW_ADMIN.getAuthority())
                .antMatchers("/api/about", "/login*", "/logout*").permitAll()
                .and()
                .httpBasic()
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .addFilterBefore(new BruteForceProtectionServletFilter(bruteForceProtectionCheckService), BasicAuthenticationFilter.class);
    }
}
