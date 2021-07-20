package com.example.security;

import com.example.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
// This class is for Authorization
public class CustomOncePerAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailServiceForJwtType2 customUserDetailServiceForJwtType2;

    public CustomOncePerAuthenticationFilter(CustomUserDetailServiceForJwtType2 customUserDetailServiceForJwtType2) {
        this.customUserDetailServiceForJwtType2 = customUserDetailServiceForJwtType2;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken != null) {

            String jwtToken = authorizationToken.replaceAll("Bearer ", "");
            UserDetails userDetails = customUserDetailServiceForJwtType2.loadUserByUsername(JwtUtil.extractUsername(jwtToken));

            // check that user should not logged in
            if (JwtUtil.validateToken(jwtToken, userDetails) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());
                usernamePasswordAuthenticationToken.setDetails(userDetails);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // Tell the spring to do all others stuff
        chain.doFilter(request, response);
    }
}
