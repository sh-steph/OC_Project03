package com.openclassrooms.occhatop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll(); // permet l'accès à la page d'accueil sans authentification
/*              .antMatchers("/admin/**").hasRole("ADMIN") // restreint l'accès à la page "/admin" aux utilisateurs ayant le rôle "ADMIN"
                .anyRequest().authenticated() // nécessite une authentification pour toutes les autres URLs
                .and()
                .formLogin(); // utilise le formulaire de login par défaut de Spring Security*/
    }
}
