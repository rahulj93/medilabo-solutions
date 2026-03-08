package com.medilabosolutions.gatewayservice.controller;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabosolutions.gatewayservice.dto.LoginRequest;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ReactiveAuthenticationManager authenticationManager; 

    public AuthController(ReactiveAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager; 
    }

    @PostMapping("/login")
    public Mono<String> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), 
                request.getPassword()
            ); 

        return authenticationManager.authenticate(authToken)
            .map(auth -> "Login successful for user: " + auth.getName()); 
    }

}
