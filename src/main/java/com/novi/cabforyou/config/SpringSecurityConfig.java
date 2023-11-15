package com.novi.cabforyou.config;

import com.novi.cabforyou.filter.JwtRequestFilter;
import com.novi.cabforyou.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.POST, "/planners/**").hasRole("PLANNER")
                        .requestMatchers(HttpMethod.GET, "/planners/**").hasRole("PLANNER")
                        .requestMatchers(HttpMethod.PUT, "/planners/**").hasRole("PLANNER")
                        .requestMatchers(HttpMethod.PATCH, "/planners/**").hasRole("PLANNER")
                        .requestMatchers(HttpMethod.DELETE, "/planners/**").hasRole("PLANNER")

                        .requestMatchers(HttpMethod.POST, "/users").hasAnyRole("USER", "PLANNER")
                        .requestMatchers(HttpMethod.GET, "/users").hasAnyRole("USER", "PLANNER")
                        .requestMatchers(HttpMethod.GET, "/users/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/users/{username}").hasAnyRole("USER", "PLANNER")
                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("PLANNER")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("PLANNER")

                        .requestMatchers(HttpMethod.GET, "/customers/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/customers/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/customers/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PATCH, "/customers/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/customers/**").hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/feedbacks/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/feedbacks/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/feedbacks/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/feedbacks/**").hasRole("USER")

                        .requestMatchers(HttpMethod.POST, "/drivers/**").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.GET, "/drivers/**").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.PUT, "/drivers/**").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.PATCH, "/drivers/**").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.DELETE, "/drivers/**").hasRole("DRIVER")

                        .requestMatchers(HttpMethod.POST, "/cars").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.GET, "/cars/**").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.PUT, "/cars/**").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.PATCH, "/cars/**").hasRole("DRIVER")
                        .requestMatchers(HttpMethod.DELETE, "/cars/**").hasRole("DRIVER")

                        .requestMatchers(HttpMethod.POST, "/bookings").hasAnyRole("USER", "PLANNER")
                        .requestMatchers(HttpMethod.GET, "/bookings/**").hasAnyRole("USER", "PLANNER")
                        .requestMatchers(HttpMethod.PUT, "/bookings/**").hasAnyRole("USER", "PLANNER")
                        .requestMatchers(HttpMethod.PATCH, "/bookings/**").hasAnyRole("USER", "PLANNER")
                        .requestMatchers(HttpMethod.GET, "/bookings/{status}").hasRole("PLANNER")

                        .requestMatchers(HttpMethod.POST, "/trips").hasRole("PLANNER")
                        .requestMatchers(HttpMethod.GET, "/trips/**").hasAnyRole("PLANNER", "DRIVER")

                        .requestMatchers(HttpMethod.GET, "/getAll").hasAnyRole("USER", "DRIVER", "PLANNER")
                        .requestMatchers(HttpMethod.GET, "/download/**").hasAnyRole("USER", "DRIVER", "PLANNER")
                        .requestMatchers(HttpMethod.POST, "/single/**").hasAnyRole("USER", "DRIVER", "PLANNER")

                        .requestMatchers("/authenticated").authenticated()
                        .requestMatchers("/authenticate").permitAll()
                        .anyRequest().denyAll())

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}