package com.example.SpringWebSecurity6JWT.DTOS;

import lombok.Data;

@Data
public class SigninRequest {
    private String email;
    private String password;
}
