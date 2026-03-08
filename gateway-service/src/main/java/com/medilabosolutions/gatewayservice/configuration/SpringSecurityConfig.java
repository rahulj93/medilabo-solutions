package com.medilabosolutions.gatewayservice.configuration;


import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;

import reactor.core.publisher.Mono;

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

    // public class RoleBasedRedirectSuccessHandler implements ServerAuthenticationSuccessHandler {

    // @Override
    // public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange,
    //                                           Authentication authentication) {
    //         URI redirectUri;
    //         if (authentication.getAuthorities().stream()
    //                 .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
    //             redirectUri = URI.create("/api/admin/hello");
    //         } else {
    //             redirectUri = URI.create("/api/user/hello");
    //         }

    //         webFilterExchange.getExchange().getResponse().setStatusCode(org.springframework.http.HttpStatus.FOUND);
    //         webFilterExchange.getExchange().getResponse().getHeaders().setLocation(redirectUri);
    //         return webFilterExchange.getExchange().getResponse().setComplete();
    //     }
    // }

    public class RoleBasedRedirectSuccessHandler implements ServerAuthenticationSuccessHandler {

        @Override
        public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange,
                                                  Authentication authentication) {
    
            // Determine where to redirect
            URI redirectUri = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) ?
                    URI.create("/api/admin/hello") :
                    URI.create("/api/user/hello");
    
            // Make sure the session is saved before sending redirect
            return webFilterExchange.getExchange().getSession()
                    .flatMap(webSession -> {
                        webSession.getAttributes().put("SPRING_SECURITY_CONTEXT", new SecurityContextImpl(authentication));
                        webFilterExchange.getExchange().getResponse().setStatusCode(org.springframework.http.HttpStatus.FOUND);
                        webFilterExchange.getExchange().getResponse().getHeaders().setLocation(redirectUri);
                        return webFilterExchange.getExchange().getResponse().setComplete();
                    });
        }
    }

    @Bean
    public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity http) {
        // Success handler: redirect users based on role after login
        // RedirectServerAuthenticationSuccessHandler successHandler = 
        //         new RedirectServerAuthenticationSuccessHandler() {
        //     @Override
        //     public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange,
        //                                               Authentication authentication) {

        //         // Redirect based on role
        //         URI redirectUri; 
        //         if (authentication.getAuthorities().stream()
        //                 .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
        //              redirectUri = URI.create("/api/admin/hello");
        //         } else {
        //             redirectUri = URI.create("/api/user/hello");
        //         }
        //         return getRedirectStrategy().sendRedirect(webFilterExchange.getExchange(), redirectUri);
        //     }
        // };

    //         // Use a redirect handler
    // RedirectServerAuthenticationSuccessHandler successHandler =
    //         new RedirectServerAuthenticationSuccessHandler() {
    //             @Override
    //             public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange,
    //                                                       Authentication authentication) {
    //                 URI redirectUri = authentication.getAuthorities().stream()
    //                         .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) ?
    //                         URI.create("/api/admin/hello") :
    //                         URI.create("/api/user/hello");
    //                 webFilterExchange.getExchange().getResponse().setStatusCode(org.springframework.http.HttpStatus.FOUND);
    //                 webFilterExchange.getExchange().getResponse().getHeaders().setLocation(redirectUri);
    //                 return webFilterExchange.getExchange().getResponse().setComplete();
    //             }
    //         };

        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .cors(cors -> {})
            .authorizeExchange(exchange -> exchange
                .pathMatchers("/auth/login").permitAll()
                .pathMatchers("/api/public/**").permitAll()
                .pathMatchers("/api/user/**").hasRole("USER")
                .pathMatchers("/api/admin/**").hasRole("ADMIN")
                .anyExchange().authenticated()
            )
            .formLogin(form -> form
                // .loginPage("/login") // optional, uses default page
                .authenticationSuccessHandler(new RoleBasedRedirectSuccessHandler())
            )
            // .formLogin(Customizer.withDefaults()) // convenient way to use out-of-the-box form-based login provided by Spring Security 
            .httpBasic(Customizer.withDefaults()) // optional, keeps HTTP Basic for API calls
            // .oauth2ResourceServer(oauth -> oauth.jwt())
            .build(); 
    }

}
