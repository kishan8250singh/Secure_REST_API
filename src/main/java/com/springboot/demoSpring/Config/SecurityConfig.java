package com.springboot.demoSpring.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // here we are making in-memory user means they store in memory nstead of Database

  //  step 1: Define Custom user for authentication
    @Bean
    public UserDetailsService userDetailsService(){
     UserDetails user = User   // user no. 1
             .withUsername("Student")
             .password(passwordEncoder().encode("5822"))
             .roles("USER")
             .build();
     UserDetails admin = User  // user no. 2
             .withUsername("Admin")
             .password(passwordEncoder().encode("admin5822"))
             .roles("ADMIN")
             .build();

     return new InMemoryUserDetailsManager(user,admin);

 }

 // step 2: Password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

 // step 3: Authorization rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf->csrf.disable()) // csrf disable so that request can be send from postman
                .authorizeHttpRequests(request->{
                    request.requestMatchers(HttpMethod.GET,"/api/student**").permitAll();

                    // Role Based Access

                    request.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
                    request.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
