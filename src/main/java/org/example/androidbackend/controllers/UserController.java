package org.example.androidbackend.controllers;

import org.example.androidbackend.models.User;
import org.example.androidbackend.repositories.UserRepository;
import org.example.androidbackend.requests.SignInRequest;
import org.example.androidbackend.requests.SignUpRequest;
import org.example.androidbackend.services.JwtService;
import org.example.androidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody SignInRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken
                            (signInRequest.getUsername(), signInRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                Object principal = authentication.getPrincipal();
                UserDetails userDetails = (UserDetails) principal;
                Optional<User> user = userRepository.findByEmail(userDetails.getUsername());
                String token;
                token = jwtService.generateToken
                        (signInRequest.getUsername(), user.get().getRole().name());
                System.out.println(user.get().getRole());
                return ResponseEntity.status(HttpStatus.OK).body(token);
            } else {
                throw new UsernameNotFoundException("Tài khoản hoặc mật khẩu không chính xác");
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Tài khoản hoặc mật khẩu không chính xác", e);
        }
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<String> handleAuthenticationException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tài khoản hoặc mật khẩu không chính xác");
    }

    @PostMapping( "/signup")
    public boolean signup(@RequestBody SignUpRequest signUpRequest){
        return userService.addUser(signUpRequest);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}