package com.tobeto.rentacar.core.security;


import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.core.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private static final String[] AVAILABLE_URLS = {
            "/v1/api-docs",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/users/findByEmail",
             "/api/v1/licenses/**"
//            "/api/v1/cars/**", "/api/v1/models/**", "/api/v1/brands/**", "/api/v1/fuels/**", "/api/v1/transmission/**"
    };

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                               x.requestMatchers(AVAILABLE_URLS).permitAll()
                                       .requestMatchers(HttpMethod.GET, "/api/v1/cars/**", "/api/v1/models/**", "/api/v1/brands/**", "/api/v1/fuels/**", "/api/v1/transmission/**").permitAll()
                                       .requestMatchers(HttpMethod.POST, "/api/v1/auth/register", "/api/v1/auth/login").permitAll()
                                       .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                                       .requestMatchers(HttpMethod.GET,"/api/v1/users/**" ).hasAnyAuthority(Role.ADMIN.name(), Role.USER.name())
                                       .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**", "/api/v1/rentals/**", "/api/v1/cars/**", "/api/v1/brands/**", "/api/v1/fuels/**", "/api/v1/transmission/**", "/api/v1/licenses/**").hasAuthority(Role.ADMIN.name())
                                       .requestMatchers(HttpMethod.POST,  "/api/v1/cars/**", "/api/v1/brands/**", "/api/v1/fuels/**", "/api/v1/transmission/**").hasAuthority(Role.ADMIN.name())
                                       .requestMatchers(HttpMethod.PUT, "/api/v1/cars/**", "/api/v1/brands/**", "/api/v1/fuels/**", "/api/v1/transmission/**").hasAuthority(Role.ADMIN.name())
                                      // .anyRequest().authenticated()
                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }
}
