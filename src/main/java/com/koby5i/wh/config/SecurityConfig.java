package com.koby5i.wh.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/items/list/**").permitAll()
                .antMatchers("/items/show/**").hasRole("USER")
                .antMatchers("/items/delete/**").hasRole("ADMIN")
                .antMatchers("/items/new/**").hasRole("ADMIN")
                .antMatchers("/items/edit/**").hasRole("ADMIN")
                .antMatchers("/items/saveorupdate/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login-error");
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("password").roles("USER").build());
//        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
//        return manager;
//
//    }

    //use=user
    // password=secret123
    private static final String USER_ENCODED_PASSWORD = "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2";


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user").password(USER_ENCODED_PASSWORD).roles("USER")
                .and()
                .withUser("admin").password(USER_ENCODED_PASSWORD).roles("USER","ADMIN");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    // @formatter:off
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .antMatchers("/css/**", "/index").permitAll()
//                                .antMatchers("/items/**").hasRole("USER")
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login")
//                                .failureUrl("/login-error")
//                );
//    }
//    // @formatter:on
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("bob")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/css/**", "/index").permitAll()
//                .antMatchers("/items/**").hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/login").failureUrl("/login-error");
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("bob").password("password").roles("USER");
//    }

//    @Autowired
//    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .inMemoryAuthentication()
//                .withUser("dan")
//                .password("password")
//                .roles("ADMIN")
//                .and()
//                .withUser("bob")
//                .password("password")
//                .roles("USER");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/items/**").hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
//    }
}
