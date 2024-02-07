package com.example.SpringWebSecurity6JWT.services.implementation;

import com.example.SpringWebSecurity6JWT.DTOS.JwtAuthenticationResponse;
import com.example.SpringWebSecurity6JWT.DTOS.RefreshTokenRequest;
import com.example.SpringWebSecurity6JWT.DTOS.SigninRequest;
import com.example.SpringWebSecurity6JWT.DTOS.SignupRequest;
import com.example.SpringWebSecurity6JWT.models.Role;
import com.example.SpringWebSecurity6JWT.models.User;
import com.example.SpringWebSecurity6JWT.repositories.UserRepository;
import com.example.SpringWebSecurity6JWT.services.AuthenticationService;
import com.example.SpringWebSecurity6JWT.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service

public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    @Autowired
    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JWTService jwTservice) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwTservice;
    }

    @Override
    public User SignUp(SignupRequest signupRequest){
        User user = new User();
        user.setName(signupRequest.getFirstName() + " " + signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPhone(signupRequest.getPhone());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse SignIn(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        User user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid Email or password"));
        var jwt = jwtService.generateToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshtoken(jwtRefreshToken);
        return jwtAuthenticationResponse;
    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.ExtractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt =  jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshtoken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return  null;

    }

}
