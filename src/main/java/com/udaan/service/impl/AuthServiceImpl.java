package com.udaan.service.impl;

import com.udaan.dto.JwtAuthResponse;
import com.udaan.dto.LoginDto;
import com.udaan.dto.RegisterDto;
import com.udaan.entity.Role;
import com.udaan.entity.User;
import com.udaan.exception.APIException;
import com.udaan.repository.RoleRepository;
import com.udaan.repository.UserRepository;
import com.udaan.security.JWTTokenProvider;
import com.udaan.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Email Already Exists!");
        }

        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setFname(registerDto.getFname());
        user.setLname(registerDto.getLname());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setMobileNo(registerDto.getMobileNo());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRole("ROLE_ADMIN");
        roles.add(userRole);

        user.setRoles(roles);

        Date date = new Date();
        user.setCreatedOn(date);
        user.setModifiedOn(date);

        userRepository.save(user);
        return "User Registered Successfully!!";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        User user = userRepository.findByEmail(loginDto.getEmail());

        String role = null;
        if(user != null){
            Optional<Role> optionalRole = user.getRoles().stream().findFirst();

            if(optionalRole.isPresent()){
                Role userRole = optionalRole.get();
                role = userRole.getRole();
            }
        }

        JwtAuthResponse response = new JwtAuthResponse();
        response.setAccessToken(token);
        response.setRole(role);

        return response;
    }
}
