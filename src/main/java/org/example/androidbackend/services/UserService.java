package org.example.androidbackend.services;

import org.example.androidbackend.requests.SignUpRequest;


public interface UserService {
    boolean addUser(SignUpRequest signUpRequest);

    String Authentication(String token);
}
