package com.devsimiyu.expensetracker.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devsimiyu.expensetracker.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityJwtFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(SecurityJwtFilter.class);

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    SecurityUserDetailsService securityUserDetailsService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // request has authorization header
        if (authorizationHeader != null) {

            // authorization header contains a Bearer token
            if (!authorizationHeader.isEmpty() || authorizationHeader.startsWith("Bearer")) {
                String token = authorizationHeader.split(" ")[1].trim();
        
                // Bearer token is valid
                if (jwtUtil.validateToken(token)) {
                    UserDetails userDetails = securityUserDetailsService.findByUsername(
                        jwtUtil.getUsername()
                    );
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, 
                        null, 
                        userDetails == null ? Collections.emptyList() : userDetails.getAuthorities()
                    );

                    authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
        
            }
        }

        filterChain.doFilter(request, response);
    }
}
