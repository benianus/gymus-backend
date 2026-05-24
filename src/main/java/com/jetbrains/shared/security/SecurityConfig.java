package com.jetbrains.shared.security;

import com.jetbrains.shared.enums.Role;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(@NonNull HttpSecurity http) throws Exception {
        return http
                .redirectToHttps(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth
                            // auth
                            .requestMatchers("/api/auth/**")
                            .permitAll()
                            // memberships & members
                            .requestMatchers(HttpMethod.GET, "/api/memberships/members")
                            .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                            .requestMatchers(HttpMethod.GET, "/api/memberships/members/{memberId}")
                            .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name, Role.MEMBER.name)
                            .requestMatchers(HttpMethod.POST, "/api/memberships/register")
                            .hasAnyRole(Role.OWNER.name)
                            .requestMatchers(
                                    HttpMethod.POST,
                                    "/api/memberships/record/{memberId}/attendance"
                            )
                            .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                            .requestMatchers(HttpMethod.POST, "/api/memberships/{memberId}/renew")
                            .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                            .requestMatchers(HttpMethod.PUT, "/api/memberships/members/{memberId}")
                            .hasAnyRole(Role.OWNER.name)
                            .requestMatchers(
                                    HttpMethod.DELETE,
                                    "/api/memberships/members/{memberId}"
                            )
                            .hasAnyRole(Role.OWNER.name)
                            // sessions
                            .requestMatchers(HttpMethod.GET, "/api/sessions")
                            .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                            .requestMatchers(HttpMethod.POST, "/api/sessions")
                            .hasAnyRole(Role.OWNER.name)
                            // store
                            .requestMatchers(HttpMethod.GET, "/api/products")
                            .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name, Role.MEMBER.name)
                            .requestMatchers(HttpMethod.GET, "/api/products/{productId}")
                            .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name, Role.MEMBER.name)
                            .requestMatchers(HttpMethod.POST, "/api/products")
                            .hasAnyRole(Role.OWNER.name)
                            .requestMatchers(HttpMethod.PUT, "/api/products/{productId}")
                            .hasAnyRole(Role.OWNER.name)
                            .requestMatchers(HttpMethod.DELETE, "/api/products/{productId}")
                            .hasAnyRole(Role.OWNER.name)
                            .requestMatchers(HttpMethod.POST, "/api/products/{productId}/sales")
                            .hasAnyRole(Role.OWNER.name, Role.EMPLOYEE.name)
                            // reports
                            .requestMatchers("/api/reports/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated();

                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager(http))
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        var authBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
