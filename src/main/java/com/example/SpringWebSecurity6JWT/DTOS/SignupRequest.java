package com.example.SpringWebSecurity6JWT.DTOS;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
