package com.raminagrobis.centraleachat.app

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun filterChain(http : HttpSecurity) : SecurityFilterChain {
        http.cors().and()
            .authorizeHttpRequests()
            .requestMatchers("/admin/**").hasAuthority("ADMIN")
            .requestMatchers("/fournisseur/**").hasAuthority("FOURNISSEUR")
            .requestMatchers("/adherent/**").hasAuthority("ADHERENT")
            .requestMatchers("/connexion").permitAll()
            .anyRequest().authenticated()

        return http.build()
    }
}