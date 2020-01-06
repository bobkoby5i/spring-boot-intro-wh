package com.koby5i.wh.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;


//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@ComponentScan
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/css/**", "/index").permitAll()
                    .antMatchers("/").permitAll()
                    //.anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/login?logout")
                        .permitAll();
                //.loginPage("/login").failureUrl("/login-error");
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
    //password=secret123
    //encodedPassword='$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2'
    //password='password
    //encodedPassword='$2a$10$Uquje0jeqhm0kzRFv1pqu.6DPS2dijCg2MTOP3ubOBPtXtIopoqrC'

    private static final String USER_ENCODED_PASSWORD = "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2";



//    private static final String host="localhost";
//    private static final String port="5432";
//    private static final String databaseName="wh_db";
//
//    // using separate db and appDataSource requires @ComponentScan
//    @Bean
//    public DataSource appDataSource2() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        //ds.setDriverClassName(org.h2.Driver.class.getName());
//        //ds.setUrl("jdbc:h2:tcp://localhost/~/myDb");
//        ds.setDriverClassName("org.postgresql.Driver");
//        ds.setUrl("jdbc:postgresql://" + host + ":" + port + "/" + databaseName);
//        ds.setUsername("wh_user");
//        ds.setPassword("wh_password");
//        return ds;
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        String password = "password";
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(password);
//        System.out.println("password='" + password + "' encodedPassword='" + encodedPassword + "'.");
//
//        // users stored in DB
//        auth.jdbcAuthentication().dataSource(appDataSource2())
//                .usersByUsernameQuery("select username, password, enabled "
//                        + "from users "
//                        + "where username = ?")
//                .authoritiesByUsernameQuery("select username, authority "
//                        + "from authorities "
//                        + "where username = ?");
//    }


    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        String password = "password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("password='" + password + "' encodedPassword='" + encodedPassword + "'.");

        // users stored in DB
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }




    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }


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
