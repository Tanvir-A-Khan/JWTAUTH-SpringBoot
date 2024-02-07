package com.example.SpringWebSecurity6JWT.controllers;

import com.example.SpringWebSecurity6JWT.DTOS.JwtAuthenticationResponse;
import com.example.SpringWebSecurity6JWT.DTOS.RefreshTokenRequest;
import com.example.SpringWebSecurity6JWT.DTOS.SigninRequest;
import com.example.SpringWebSecurity6JWT.DTOS.SignupRequest;
import com.example.SpringWebSecurity6JWT.models.User;
import com.example.SpringWebSecurity6JWT.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authenticationService.SignUp(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.SignIn(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
