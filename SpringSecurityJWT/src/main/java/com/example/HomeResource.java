package com.example;

import com.example.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @GetMapping("/user")
    private String welcomeUser() {
        return "Welcome user";
    }

    @PostMapping("/authenticate")
    private String getJwtToken(@RequestParam String username, @RequestParam String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        return JwtUtil.generateToken(customUserDetailService.loadUserByUsername(username));

    }

}
