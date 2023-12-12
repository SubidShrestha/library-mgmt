package com.fruits.library.config;

import com.fruits.library.userConfig.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/accounts/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/books/**").hasAnyRole(Role.ADMIN.name(),Role.USER.name())
                                .requestMatchers(HttpMethod.POST,"/api/books/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.PUT,"/api/books/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/books/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.GET,"/api/member/**").hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/api/member/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.PUT,"/api/member/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/member/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.GET,"/api/borrow/**").hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/api/borrow/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.PUT,"/api/borrow/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/borrow/**").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        return httpSecurity.build();
    }
}
