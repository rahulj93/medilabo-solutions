package com.medilabosolutions.gatewayservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the gateway!";
    }

    @GetMapping("/api/public/hello")
    public String publicEndpoint() {
        return "Public endpoint - no authentication required";
    }

    @GetMapping("/api/user/hello")
    public String userEndpoint() {
        return "Hello USER";
    }

    @GetMapping("/api/admin/hello")
    public String adminEndpoint() {
        return "Hello ADMIN";
    }
    
    @GetMapping("/api/user/me")
    public String getCurrentUser(java.security.Principal principal) {
        if (principal != null) {
            System.out.println(principal.getName());
        }
        return principal.getName(); // Returns username if logged in, otherwise Spring returns 401 
    }
}