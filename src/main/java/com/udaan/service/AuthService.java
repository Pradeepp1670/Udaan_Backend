package com.udaan.service;

import com.udaan.dto.JwtAuthResponse;
import com.udaan.dto.LoginDto;
import com.udaan.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
