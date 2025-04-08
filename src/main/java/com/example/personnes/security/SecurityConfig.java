package com.example.personnes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // ✅ Création des utilisateurs en mémoire (admin & user)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(
                User.withUsername("user")
                        .password(encoder.encode("user123"))
                        .roles("USER")
                        .build());

        userDetailsManager.createUser(
                User.withUsername("admin")
                        .password(encoder.encode("admin123"))
                        .roles("ADMIN")
                        .build());

        return userDetailsManager;
    }

    // ✅ Configuration des règles de sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        // Permettre les requêtes publiques
                        .requestMatchers("/api/public/**").permitAll()

                        // Protéger les routes admin
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Toute autre requête nécessite une authentification
                        .anyRequest().authenticated()
                )
                // ✅ Utilisation de l'authentification HTTP Basic
                .httpBasic(Customizer.withDefaults())

                // ✅ Désactiver CSRF pour Postman/test (à ne pas faire en prod avec forms)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // ✅ Encoder les mots de passe avec BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
