package com.udaan.controller;

import com.udaan.dto.JwtAuthResponse;
import com.udaan.dto.LoginDto;
import com.udaan.dto.RegisterDto;
import com.udaan.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private AuthService auth;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = auth.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        JwtAuthResponse jwtAuthResponse = auth.login(loginDto);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}