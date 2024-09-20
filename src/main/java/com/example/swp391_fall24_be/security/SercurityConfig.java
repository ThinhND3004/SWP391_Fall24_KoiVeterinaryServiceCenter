package com.example.swp391_fall24_be.security;

import com.example.swp391_fall24_be.security.filter.CustomeJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SercurityConfig {
    @Autowired
    private CustomeJwtFilter customeJwtFilter;

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

//        Không phải bị lỗi nha ae, chỉ là cú pháp này sắp hết date thôi :)))
        return httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .build();

    }
}
