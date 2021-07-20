package com.example.security;

import com.example.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
// this class is for validate the token and set the access
public class customOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken != null) {

            String jwtToken = authorizationToken.replaceAll("Bearer ", "");
            UserDetails userDetails = customUserDetailService.loadUserByUsername(JwtUtil.extractUsername(jwtToken));

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
