package com.rayyanhunerkar.todolist.util.security;

import com.rayyanhunerkar.todolist.service.UserService;
import com.rayyanhunerkar.todolist.util.jwt.JwtAuthenticationEntryPoint;
import com.rayyanhunerkar.todolist.util.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    private final String[] AUTH_WHITELIST = {
            "/auth/**",
            "/swagger-ui/**",
            "/api-docs/**"
    };
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/login")
                                .disable()
                )
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(AUTH_WHITELIST)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .exceptionHandling(
                        httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

