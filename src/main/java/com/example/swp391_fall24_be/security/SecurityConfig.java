package com.example.swp391_fall24_be.security;

import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.security.filter.CustomerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableWebSecurity
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

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(customerFilter, AuthorizationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // PUBLIC ACCESS
                        .requestMatchers("/auth/**","/swagger-ui/**", "/vnpay/**")
                            .permitAll()
                        .requestMatchers(HttpMethod.POST, "/accounts/**")
                            .permitAll()

                        .requestMatchers(HttpMethod.GET,"/koi-species/**", "/services/**","/prescriptions/**","/ponds/**","/shipping/**", "/accounts/**", "/images/picture/**")
                            .permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                            .permitAll()

                        // HAS AN ACCOUNT
                        .requestMatchers("/bookings/**", "/images/**","/notifications/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.GET,"/accounts/**", "/timetables/**", "/bookings/**")
                        .authenticated()

                        // CUSTOMER
                        .requestMatchers(HttpMethod.POST, "/bookings/**", "/feedbacks/**")
                        .hasAuthority(AccountRoleEnum.CUSTOMER.name())

                        // VETERIAN
                        .requestMatchers(HttpMethod.POST, "/timetables/**", "/reports/**")
                        .hasAuthority(AccountRoleEnum.VETERIAN.name())

                        // ADMIN, STAFF, MANAGER
                        .requestMatchers("/koi-species/**", "/services/**","/prescriptions/**", "/prescription-medicine/**", "/ponds/**", "/medicine-batches/**",
                                "/shipping/**")
                        .hasAnyAuthority(AccountRoleEnum.ADMIN.name(), AccountRoleEnum.STAFF.name(),AccountRoleEnum.MANAGER.name())

                        // MANAGER, ADMIN
                        .requestMatchers("/dashboard/**")
                        .hasAnyAuthority(AccountRoleEnum.ADMIN.name(), AccountRoleEnum.MANAGER.name())

                        .anyRequest().permitAll())
                .build();
    }
}
