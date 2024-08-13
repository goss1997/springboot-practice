package com.example.jwt_secure_login.controller;

import com.example.jwt_secure_login.model.User;
import com.example.jwt_secure_login.repository.UserRepository;
import com.example.jwt_secure_login.service.UserDetailsServiceImpl;
import com.example.jwt_secure_login.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsService;


    @PostMapping("/authenticate")
    public Map<String, String> createAuthenticationToken(@RequestBody Map<String, String> userCred) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCred.get("email"), userCred.get("password"))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userCred.get("email"));
        final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("jwt", jwt);
        return response;
    }


}
