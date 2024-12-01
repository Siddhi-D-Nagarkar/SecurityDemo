package com.sid.SecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
//    @Bean
//    public UserDetailsService inMemoryUserDetailsManager() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("developer").password("password").roles("DEVELOPER").build());
//        manager.createUser(User.withUsername("admin").password("password").roles("ADMIN").build());
//        return manager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    Authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/developer/**").hasAuthority("DEVELOPER")
                        .requestMatchers("/home/**").permitAll()
                        .requestMatchers("/tester/**").hasAnyAuthority("DEVELOPER", "ADMIN")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                ).formLogin(withDefaults())
                .httpBasic(withDefaults()).csrf(csrf ->csrf.disable());
        return http.build();
    }

//    @Bean
//    JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails developer = User.builder()
//                .username("developer")
//                .password("$2a$10$PVeAPzqdZgSuLPBLklttdewPcurZk69Pr5vmf9HVDrx8gAp6PGYY6")
//                .authorities("DEVELOPER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("$2a$10$mrKQj1.6gSRoFG/srSazrOKwnv7LKIRm5CMMoxh.zmsVSaU3aM08a")
//                .authorities("ADMIN")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(developer);
//        users.createUser(admin);
//        return users;
//    }

}
