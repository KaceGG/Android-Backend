package org.example.androidbackend.response;


import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String role;
}
