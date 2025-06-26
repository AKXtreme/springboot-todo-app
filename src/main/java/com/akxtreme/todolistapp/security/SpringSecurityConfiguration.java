package com.akxtreme.todolistapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails user1 = createNewUser("ak", "ak");
        UserDetails user2 = createNewUser("maru", "maru");
        return new InMemoryUserDetailsManager(user1, user2);
    }

    private UserDetails createNewUser(String username, String password) {
        return User.builder()
                .username(username)
                .password(passwordEncoder().encode(password))
                .roles("USER", "ADMIN")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF to allow H2 console form to work
                .csrf(csrf -> csrf.disable())

                // Allow H2 console to be rendered in a frame
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                // Define which URLs are public and which require auth
                .authorizeHttpRequests(auth -> auth
                        // Public: root, welcome, H2 console and Actuator
                        .requestMatchers("/", "/welcome", "/h2-console/**", "/actuator/**").permitAll()
                        // Static resources
                        .requestMatchers("/webjars/**", "/css/**", "/js/**").permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )

                // Use Spring Security's built-in login page and allow everyone to access it
                .formLogin(form -> form
                        .permitAll()
                )

                // Enable logout for everyone
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }
}
