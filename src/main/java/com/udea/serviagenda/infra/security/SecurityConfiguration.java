package com.udea.serviagenda.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.udea.serviagenda.dominio.user.model.Permission.*;
import static com.udea.serviagenda.dominio.user.model.Role.ADMIN;
import static com.udea.serviagenda.dominio.user.model.Role.EMPLOYE;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionAuthenticationStrategy -> sessionAuthenticationStrategy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(rQ -> {
                    rQ.requestMatchers("/clients/**").permitAll();
                    rQ.requestMatchers("/employes/**").hasRole(ADMIN.name());
                    rQ.requestMatchers("/services/**").hasAnyRole(ADMIN.name(), EMPLOYE.name());
                    rQ.requestMatchers(GET,"/services/**").hasAnyAuthority(ADMIN_READ.name(), EMPLOYE_READ.name());
                    rQ.requestMatchers(POST,"/services/**").hasAnyAuthority(ADMIN_CREATE.name(), EMPLOYE_CREATE.name());
                    rQ.requestMatchers(PUT,"/services/**").hasAnyAuthority(ADMIN_UPDATE.name(), EMPLOYE_UPDATE.name());
                    rQ.requestMatchers(DELETE,"/services/**").hasAnyAuthority(ADMIN_DELETE.name(), EMPLOYE_DELETE.name());
                    rQ.requestMatchers("/auth/**").permitAll();
                    rQ.requestMatchers("/citation/**").permitAll();
                    rQ.anyRequest().authenticated();
                })
                .cors(Customizer.withDefaults())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}