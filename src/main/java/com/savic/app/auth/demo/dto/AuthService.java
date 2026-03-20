package com.savic.app.auth.demo.dto;

import com.savic.app.auth.demo.entity.User;
import com.savic.app.auth.demo.repository.Repository;
import com.savic.app.auth.demo.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Repository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(Repository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //Registracija korisnika
    public String register(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        repository.save(user);

        return "User registered successfully";
    }

    // Login korisnika
    public AuthResponse login(LoginRequest request) {

        User user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    public String deleteUser(Long id) {
        if (!repository.existsById(id)) {
            return "User not found";
        }

        repository.deleteById(id);
        return "User deleted successfully";
    }

}