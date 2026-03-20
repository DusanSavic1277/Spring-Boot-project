package com.savic.app.auth.demo.controller;

import com.savic.app.auth.demo.dto.AuthResponse;
import com.savic.app.auth.demo.dto.AuthService;
import com.savic.app.auth.demo.dto.LoginRequest;
import com.savic.app.auth.demo.dto.RegisterRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    
    private final AuthService authService;
    
    public AuthController(AuthService authService){
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }
    
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
    
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        return authService.deleteUser(id);
    }
    
}