package com.medilabosolutions.gatewayservice.controller;

import java.util.Map;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import com.medilabosolutions.gatewayservice.dto.LoginRequest;
import com.medilabosolutions.gatewayservice.util.JwtUtil;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ReactiveAuthenticationManager authenticationManager; 
    private final JwtUtil jwUtil; 

    public AuthController(ReactiveAuthenticationManager authenticationManager, JwtUtil jwUtil) {
        this.authenticationManager = authenticationManager; 
        this.jwUtil = jwUtil; 
    }

    @PostMapping("/login")
    public Mono<Map<String, String>> login(@RequestBody LoginRequest request, WebSession session) {
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), 
                request.getPassword()
            ); 

        return authenticationManager.authenticate(authToken)
            .map(auth -> Map.of("token", jwUtil.generateToken(auth))); // Return JWT as plain text 
    }
}
