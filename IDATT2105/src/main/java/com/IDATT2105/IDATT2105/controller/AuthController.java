package com.IDATT2105.IDATT2105.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.IDATT2105.IDATT2105.security.JwtUtil;
import com.IDATT2105.IDATT2105.security.SecretKeyGenerator;

import io.jsonwebtoken.ExpiredJwtException;

import com.IDATT2105.IDATT2105.model.LoginRequest;
import com.IDATT2105.IDATT2105.model.JwtResponse;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = JwtUtil.generateToken(userDetails.getUsername(), SecretKeyGenerator.generateSecretKey());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String expiredTokenHeader) {
        String expiredToken = expiredTokenHeader.substring(7);
        String username;
        try {
            username = JwtUtil.extractUsername(expiredToken);
        } catch (ExpiredJwtException e) {
            // If token is expired, generate a new token using the username extracted from the expired token
            String newToken = JwtUtil.generateToken(username, SecretKeyGenerator.generateSecretKey());
            return ResponseEntity.ok(new JwtResponse(newToken));
        }
        // If token is not expired, return a bad request response indicating that the token is still valid
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is still valid");
    }
}
