package com.medilabosolutions.gatewayservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.medilabosolutions.gatewayservice.util.JwtUtil;

@Configuration
@EnableWebFluxSecurity
public class SpringSecurityConfig {

    // Since we are not extending WebSecurityConfigurerAdapter we have to do the following: 
    // 1. Replace the AuthenticationManagerBuilder method with this Bean
    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User
            .withUsername("springuser")
            .password((encoder.encode("spring123")))
            .roles("USER")
            .build(); 
        UserDetails admin = User.withUsername("springadmin")
            .password((encoder.encode("admin123")))
            .roles("ADMIN", "USER")
            .build(); 

        return new MapReactiveUserDetailsService(user, admin); 
    }

    // 2. You MUST provide an encoder bean 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager (
        MapReactiveUserDetailsService userDetailsService,
        PasswordEncoder encoder
    ) {
        UserDetailsRepositoryReactiveAuthenticationManager authManager = new UserDetailsRepositoryReactiveAuthenticationManager(
            userDetailsService
        ); 
        authManager.setPasswordEncoder(encoder);
        return authManager;  
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // allow cookies
        config.addAllowedOrigin("http://localhost:3000"); // React dev server
        config.addAllowedOrigin("http://localhost:5183"); // Vite dev server inside Docker 
        config.addAllowedHeader("*");
        config.addAllowedMethod("*"); // GET, POST, etc.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

    @Bean
    public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity http, JwtUtil jwtUtil) {
        return http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .cors(ServerHttpSecurity.CorsSpec::disable) // we handle CORS globally via CorsWebFilter
        .formLogin(form -> form.disable())
        .httpBasic(httpBasic -> httpBasic.disable())
        .exceptionHandling(exceptions -> 
            exceptions.authenticationEntryPoint((exchange, ex) -> {
                // Attach CORS headers manually so browser doesn't block
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Credentials", "true");
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            })
        )
        .authorizeExchange(exchange -> exchange
            .pathMatchers(HttpMethod.OPTIONS).permitAll() // allow preflight
            .pathMatchers("/auth/login").permitAll()
            .pathMatchers("/api/public/**").permitAll()
            .pathMatchers("/patient/**").permitAll()
            .pathMatchers("/patients").permitAll()
            .pathMatchers("/notes").permitAll()
            .pathMatchers("/risk-assessment/**").permitAll()
            .pathMatchers("/api/user/**").hasRole("USER")
            .pathMatchers("/api/admin/**").hasRole("ADMIN")
            .anyExchange().authenticated()
        )
        .addFilterAfter(new JwtAuthenticationFilter(jwtUtil), SecurityWebFiltersOrder.AUTHENTICATION)
        .build();
    }
}
