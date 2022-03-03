package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.DTO.auth.AuthenticationRequest;
import com.benahmed.gestiondestock.DTO.auth.AuthenticationResponse;
import com.benahmed.gestiondestock.service.auth.ApplicationUserDetailsService;
import com.benahmed.gestiondestock.utilis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.benahmed.gestiondestock.utilis.constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken(jwt).build());
    }
}
