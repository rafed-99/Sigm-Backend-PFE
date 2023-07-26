package com.example.sigmback.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.sigmback.model.Permission.*;
import static com.example.sigmback.model.Role.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
            .cors(Customizer.withDefaults())
        .authorizeHttpRequests()
        .requestMatchers(
                "/api/v1/auth/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html"
//                "/api/**"

        )
          .permitAll()

//
        .requestMatchers("/api/geologie/point/**").hasAnyRole(GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name(),GEOLOGIE_CONSULT.name())
        .requestMatchers(GET, "/api/geologie/point/**").hasAnyAuthority(GEOLOGIE_ADMIN_READ.name(), GEOLOGIE_USER_READ.name(), GEOLOGIE_CONSULT_READ.name())
        .requestMatchers(POST, "/api/geologie/point/**").hasAnyAuthority(GEOLOGIE_ADMIN_CREATE.name(), GEOLOGIE_USER_CREATE.name())
        .requestMatchers(PUT, "/api/geologie/point/**").hasAnyAuthority(GEOLOGIE_ADMIN_UPDATE.name(), GEOLOGIE_USER_UPDATE.name())
        .requestMatchers(DELETE, "/api/geologie/point/**").hasAuthority(GEOLOGIE_ADMIN_DELETE.name())

        .requestMatchers("/api/geologie/gisement/**").hasAnyRole(GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name(),GEOLOGIE_CONSULT.name())
        .requestMatchers(GET, "/api/geologie/gisement/**").hasAnyAuthority(GEOLOGIE_ADMIN_READ.name(), GEOLOGIE_USER_READ.name(), GEOLOGIE_CONSULT_READ.name())
        .requestMatchers(POST, "/api/geologie/gisement/**").hasAnyAuthority(GEOLOGIE_ADMIN_CREATE.name(), GEOLOGIE_USER_CREATE.name())
        .requestMatchers(PUT, "/api/geologie/gisement/**").hasAnyAuthority(GEOLOGIE_ADMIN_UPDATE.name(), GEOLOGIE_USER_UPDATE.name())
        .requestMatchers(DELETE, "/api/geologie/gisement/**").hasAuthority(GEOLOGIE_ADMIN_DELETE.name())

        .requestMatchers("/api/geologie/geo/**").hasAnyRole(GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name(),GEOLOGIE_CONSULT.name())
        .requestMatchers(GET, "/api/geologie/geo/**").hasAnyAuthority(GEOLOGIE_ADMIN_READ.name(), GEOLOGIE_USER_READ.name(), GEOLOGIE_CONSULT_READ.name())
        .requestMatchers(POST, "/api/geologie/geo/**").hasAnyAuthority(GEOLOGIE_ADMIN_CREATE.name(), GEOLOGIE_USER_CREATE.name())
        .requestMatchers(PUT, "/api/geologie/geo/**").hasAnyAuthority(GEOLOGIE_ADMIN_UPDATE.name(), GEOLOGIE_USER_UPDATE.name())
        .requestMatchers(DELETE, "/api/geologie/geo/**").hasAuthority(GEOLOGIE_ADMIN_DELETE.name())

        .requestMatchers("/api/geologie/couche/**").hasAnyRole(GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name(),GEOLOGIE_CONSULT.name())
        .requestMatchers(GET, "/api/geologie/couche/**").hasAnyAuthority(GEOLOGIE_ADMIN_READ.name(), GEOLOGIE_USER_READ.name(), GEOLOGIE_CONSULT_READ.name())
        .requestMatchers(POST, "/api/geologie/couche/**").hasAnyAuthority(GEOLOGIE_ADMIN_CREATE.name(), GEOLOGIE_USER_CREATE.name())
        .requestMatchers(PUT, "/api/geologie/couche/**").hasAnyAuthority(GEOLOGIE_ADMIN_UPDATE.name(), GEOLOGIE_USER_UPDATE.name())
        .requestMatchers(DELETE, "/api/geologie/couche/**").hasAuthority(GEOLOGIE_ADMIN_DELETE.name())

            .requestMatchers("/api/centre/element/**").hasAnyRole(CENTRE_ADMIN.name(),CENTRE_CONFIRM.name(),CENTRE_USER.name(),GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name(),GEOLOGIE_CONSULT.name())
            .requestMatchers(GET, "/api/centre/element/**").hasAnyAuthority(CENTRE_ADMIN_READ.name(), CENTRE_CONFIRM_READ.name(), CENTRE_USER_READ.name(),GEOLOGIE_ADMIN_READ.name(), GEOLOGIE_USER_READ.name(), GEOLOGIE_CONSULT_READ.name())
            .requestMatchers(POST, "/api/centre/element/**").hasAnyAuthority(CENTRE_ADMIN_CREATE.name(), CENTRE_CONFIRM_CREATE.name(),CENTRE_USER_CREATE.name())
            .requestMatchers(PUT, "/api/centre/element/**").hasAnyAuthority(CENTRE_ADMIN_UPDATE.name(), CENTRE_CONFIRM_UPDATE.name(),CENTRE_USER_UPDATE.name())
            .requestMatchers(DELETE, "/api/centre/element/**").hasAnyAuthority(CENTRE_ADMIN_DELETE.name(),CENTRE_CONFIRM_DELETE.name(),CENTRE_USER_DELETE.name())

            .requestMatchers("/api/centre/coupure/**").hasAnyRole(CENTRE_ADMIN.name(),CENTRE_CONFIRM.name(),CENTRE_USER.name())
            .requestMatchers(GET, "/api/centre/coupure/**").hasAnyAuthority(CENTRE_ADMIN_READ.name(), CENTRE_CONFIRM_READ.name(), CENTRE_USER_READ.name())

            .requestMatchers("/api/analyse/**").hasAnyRole(CENTRE_ADMIN.name(),CENTRE_CONFIRM.name(),CENTRE_USER.name(),GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name(),GEOLOGIE_CONSULT.name())
            .requestMatchers(GET, "/api/analyse/**").hasAnyAuthority(CENTRE_ADMIN_READ.name(), CENTRE_CONFIRM_READ.name(), CENTRE_USER_READ.name(),GEOLOGIE_ADMIN_READ.name(), GEOLOGIE_USER_READ.name(), GEOLOGIE_CONSULT_READ.name())
            .requestMatchers(POST, "/api/analyse/**").hasAnyAuthority(CENTRE_ADMIN_CREATE.name(), CENTRE_CONFIRM_CREATE.name(),CENTRE_USER_CREATE.name())
            .requestMatchers(PUT, "/api/analyse/**").hasAnyAuthority(CENTRE_ADMIN_UPDATE.name(), CENTRE_CONFIRM_UPDATE.name(),CENTRE_USER_UPDATE.name())
            .requestMatchers(DELETE, "/api/analyse/**").hasAnyAuthority(CENTRE_ADMIN_DELETE.name(),CENTRE_CONFIRM_DELETE.name(),CENTRE_USER_DELETE.name())

            .requestMatchers("/api/echantillon/**").hasAnyRole(CENTRE_ADMIN.name(),CENTRE_CONFIRM.name(),CENTRE_USER.name(),GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name(),GEOLOGIE_CONSULT.name())
            .requestMatchers(GET, "/api/echantillon/**").hasAnyAuthority(CENTRE_ADMIN_READ.name(), CENTRE_CONFIRM_READ.name(), CENTRE_USER_READ.name(),GEOLOGIE_ADMIN_READ.name(), GEOLOGIE_USER_READ.name(), GEOLOGIE_CONSULT_READ.name())
            .requestMatchers(POST, "/api/echantillon/**").hasAnyAuthority(GEOLOGIE_ADMIN_CREATE.name(), GEOLOGIE_USER_CREATE.name())
            .requestMatchers(PUT, "/api/echantillon/**").hasAnyAuthority(GEOLOGIE_ADMIN_UPDATE.name(), GEOLOGIE_USER_UPDATE.name())
            .requestMatchers(DELETE, "/api/echantillon/**").hasAuthority(GEOLOGIE_ADMIN_DELETE.name())

            .requestMatchers("/api/archive/**").hasAnyRole(CENTRE_ADMIN.name(),CENTRE_CONFIRM.name(),CENTRE_USER.name(),GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name(),GEOLOGIE_CONSULT.name())
            .requestMatchers(GET, "/api/archive/**").hasAnyAuthority(GEOLOGIE_ADMIN_READ.name(), GEOLOGIE_USER_READ.name(), GEOLOGIE_CONSULT_READ.name(),CENTRE_ADMIN_READ.name(), CENTRE_CONFIRM_READ.name(), CENTRE_USER_READ.name())
            .requestMatchers(POST, "/api/archive/**").hasAnyAuthority(GEOLOGIE_ADMIN_CREATE.name(), GEOLOGIE_USER_CREATE.name(),CENTRE_ADMIN_CREATE.name(), CENTRE_CONFIRM_CREATE.name(),CENTRE_USER_CREATE.name())
            .requestMatchers(PUT, "/api/archive/**").hasAnyAuthority(GEOLOGIE_ADMIN_UPDATE.name(), GEOLOGIE_USER_UPDATE.name(),CENTRE_ADMIN_UPDATE.name(), CENTRE_CONFIRM_UPDATE.name(),CENTRE_USER_UPDATE.name())
            .requestMatchers(DELETE, "/api/archive/**").hasAnyAuthority(GEOLOGIE_ADMIN_DELETE.name(),CENTRE_ADMIN_DELETE.name(),CENTRE_CONFIRM_DELETE.name(),CENTRE_USER_DELETE.name())

            .requestMatchers("/api/bordereau/**").hasAnyRole(CENTRE_ADMIN.name(),CENTRE_CONFIRM.name(),CENTRE_USER.name(),GEOLOGIE_ADMIN.name(),GEOLOGIE_USER.name())
            .requestMatchers(GET, "/api/bordereau/**").hasAnyAuthority(CENTRE_ADMIN_READ.name(), CENTRE_CONFIRM_READ.name(), CENTRE_USER_READ.name())
            .requestMatchers(POST, "/api/bordereau/**").hasAnyAuthority(GEOLOGIE_ADMIN_CREATE.name(), GEOLOGIE_USER_CREATE.name())
            .requestMatchers(PUT, "/api/bordereau/**").hasAnyAuthority(CENTRE_ADMIN_UPDATE.name(), CENTRE_CONFIRM_UPDATE.name(),CENTRE_USER_UPDATE.name())
            .requestMatchers(DELETE, "/api/bordereau/**").hasAnyAuthority(GEOLOGIE_ADMIN_DELETE.name(),CENTRE_ADMIN_DELETE.name(),CENTRE_CONFIRM_DELETE.name(),CENTRE_USER_DELETE.name())

////        .requestMatchers("/api/user/admin/**").hasRole(USER.name())
            .requestMatchers("/api/admin/user/**").hasRole(ADMIN.name())
////
////        .requestMatchers(GET, "/api/user/admin/**").hasAuthority(USER_READ.name())
//
            .requestMatchers(GET, "/api/admin/user/**").hasAuthority(ADMIN_READ.name())
        .requestMatchers(PUT, "/api/admin/user/**").hasAuthority(ADMIN_UPDATE.name())
        .requestMatchers(DELETE, "/api/admin/user/**").hasAuthority(ADMIN_DELETE.name())


        .anyRequest()
          .authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}
