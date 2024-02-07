package com.example.SpringWebSecurity6JWT.DTOS;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshtoken;
}
