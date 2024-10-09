package com.example.swp391_fall24_be.security;

import com.example.swp391_fall24_be.security.filter.CustomerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  
public class SecurityConfig {
    @Autowired
    private CustomerFilter customerFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        CÚ PHÁP JAVA 8
//         httpSecurity.csrf(c -> c.disable())
//                .authorizeHttpRequests(a -> {
//                    a.requestMatchers("/**");
//                    a.anyRequest().authenticated();
//                }
//                );
//
//         return httpSecurity.build();

        return httpSecurity.csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sets session management policy to stateless
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permits all requests to any path
                        .requestMatchers(HttpMethod.GET, "/accounts")
                            .authenticated()

                        .anyRequest().permitAll()) // Requires authentication for any other requests
                .build(); // Builds the security filter chain
    }
}
