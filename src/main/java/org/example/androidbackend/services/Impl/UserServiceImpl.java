package org.example.androidbackend.services.Impl;

import org.example.androidbackend.models.Role;
import org.example.androidbackend.models.User;
import org.example.androidbackend.repositories.UserRepository;
import org.example.androidbackend.requests.SignUpRequest;
import org.example.androidbackend.services.JwtService;
import org.example.androidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtService jwtService;
    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Optional<User> users = userRepository.findByEmail(signUpRequest.getUsername());
        if (users.isEmpty()) {
            User user = new User();
            user.setEmail(signUpRequest.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
            user.setRole(Role.CUSTOMER);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String Authentication(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtService.extractUsername(token);
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return username;
        } else {
            return null;
        }
    }
}
