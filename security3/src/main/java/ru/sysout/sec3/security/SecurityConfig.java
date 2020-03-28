package ru.sysout.sec3.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                .password("password")
                .authorities("ROLE_ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/**")
                .authorizeRequests(a -> a
                        .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll())
                .formLogin();

    }


    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class AnotherSecurityConfig extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .antMatcher("/admin/**")
                    .authorizeRequests(a -> a.anyRequest().hasAnyRole("ADMIN"))
                    .httpBasic();

        }
    }

}

