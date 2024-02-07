package com.example.SpringWebSecurity6JWT.services;

import com.example.SpringWebSecurity6JWT.DTOS.JwtAuthenticationResponse;
import com.example.SpringWebSecurity6JWT.DTOS.RefreshTokenRequest;
import com.example.SpringWebSecurity6JWT.DTOS.SigninRequest;
import com.example.SpringWebSecurity6JWT.DTOS.SignupRequest;
import com.example.SpringWebSecurity6JWT.models.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    User SignUp(SignupRequest signupRequest);
    JwtAuthenticationResponse SignIn(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
