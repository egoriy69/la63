package com.example.diplom33.controllers;


import com.example.diplom33.dto.*;
import com.example.diplom33.exceptions.NoSuchException;
import com.example.diplom33.models.RefreshToken;
import com.example.diplom33.security.JwtUtil;
import com.example.diplom33.services.AuthService;
import com.example.diplom33.services.RefreshTokenService;
import com.example.diplom33.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getPhone(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {

            throw new NoSuchException("Неправильный логин или пароль");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getPhone());

        return ResponseEntity.ok(JwtResponse.builder()
                .token(jwtUtil.generateToken(userDetails))
                .refreshToken(refreshTokenService.createRefreshToken(jwtRequest.getPhone()).getToken())
                .build());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid RegistrationUserDTO registrationUser) {
        authService.createUser(registrationUser);
        return new ResponseEntity<>("пользователь создан", HttpStatus.OK);
    }

    @PostMapping("/signout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        authService.logout(principal);
        return new ResponseEntity<>("пользователь вышел", HttpStatus.OK);
    }


    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.getPhone()));
                    return JwtResponse.builder()
                            .token(token)
                            .refreshToken(refreshTokenRequest.getRefreshToken())
                            .build();
                }).orElseThrow(() -> new NoSuchException("Нужна повторная авторизация"));
    }

    @GetMapping("/user")
    public UserWithRoleDTO getFullName(Principal principal){
        return userService.getUserWithRole(principal);
    }
}

