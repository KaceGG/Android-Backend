package org.example.androidbackend.services;

import org.example.androidbackend.request.SignUpRequest;
import org.springframework.stereotype.Service;


public interface UserService {
    boolean addUser(SignUpRequest signUpRequest);
}
