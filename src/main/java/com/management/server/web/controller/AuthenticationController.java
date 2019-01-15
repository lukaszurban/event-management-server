package com.management.server.web.controller;

import com.management.server.security.TokenProvider;
import com.management.server.security.UserDetailsServiceImpl;
import com.management.server.web.dto.AuthRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

/**
 * Created by Łukasz on 13.01.2019
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api/auth")
public class AuthenticationController extends AbstractController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    TokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody AuthRequestDto requestDto) {
        Authentication auth = authenticate(requestDto);
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUsername());
        log.info(MessageFormat.format("User {0} signed in successfully!", requestDto.getUsername()));

        return ResponseEntity.ok(tokenProvider.generateToken(userDetails));
    }

    private Authentication authenticate(AuthRequestDto requestDto) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUsername(),
                        requestDto.getPassword()
                )
        );
    }
}
