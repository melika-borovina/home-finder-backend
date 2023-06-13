package com.ssst.homefinderbackend.config;

import com.ssst.homefinderbackend.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF not needed
        http.csrf().disable()
                // enabled cors
                .cors().and()
                .authorizeHttpRequests((authz) -> authz
                        // don't authenticate this particular request
                        .requestMatchers("/authenticate",
                                "/api/user/register",
                                "/api/user/{username}",
                                        "/api/real-estate/**",
                                "/api/feature/**",
                                "/api/feature",
                                        "/api/real-estate",
                                        "api/tour/list", "api/tour",
                                        "api/tour/**",
                                        "/api/review/**",
                                "/api/article/list",
                                "/api/article/{id}",
                                "/api/article",
                                "/api/customer-support",
                                "/api/customer-support/{id}",
                                "/api/customer-support/list",
                                "/api/contact-us",
                                "/api/contact-us/list",
                                "/api/contact-us/{id}").permitAll()

                        // all other requests need to be authenticated
                        .anyRequest().authenticated()
                )
                // exceptions handling
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                // make sure stateless session is used; session won't be used to store user's state
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}