package com.intranet.onlineshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomLoginSecurityHandler customLoginSecurityHandler;

    public ApplicationSecurityConfiguration(CustomLoginSecurityHandler customLoginSecurityHandler) {
        this.customLoginSecurityHandler = customLoginSecurityHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**").permitAll()
                .antMatchers("/", "/users/register/**", "/users/login").anonymous()
                .antMatchers("/support/*").hasAnyAuthority("ROLE_SUPPORT", "ROLE_CUSTOMER", "ROLE_SELLER","ROLE_ORDER_MANAGER")
                .antMatchers("/seller/**").hasAuthority("ROLE_SELLER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customLoginSecurityHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/");
    }
}
