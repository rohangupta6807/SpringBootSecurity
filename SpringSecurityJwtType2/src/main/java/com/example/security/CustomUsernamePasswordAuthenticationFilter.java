package com.example.security;

import com.example.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
// This class is for Authentication
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailServiceForJwtType2 customUserDetailServiceForJwtType2;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      CustomUserDetailServiceForJwtType2 customUserDetailServiceForJwtType2) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.customUserDetailServiceForJwtType2 = customUserDetailServiceForJwtType2;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Incorrect username or password", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = request.getParameter("username");
        String token = JwtUtil.generateToken(customUserDetailServiceForJwtType2.loadUserByUsername(username));
        response.addHeader("Authorization", "Bearer " + token);
    }
}
