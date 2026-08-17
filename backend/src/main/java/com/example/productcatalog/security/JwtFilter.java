package com.example.productcatalog.security;

import com.example.productcatalog.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        System.out.println(
                "JWT FILTER: "
                        + request.getMethod()
                        + " "
                        + request.getRequestURI()
        );

        // ==========================================
        // NO AUTHORIZATION HEADER
        // ==========================================

        if (header == null || header.isBlank()) {

            System.out.println(
                    "JWT FILTER: No Authorization header"
            );

            filterChain.doFilter(request, response);
            return;
        }

        // ==========================================
        // INVALID AUTHORIZATION HEADER
        // ==========================================

        if (!header.startsWith("Bearer ")) {

            System.out.println(
                    "JWT FILTER: Invalid Authorization header"
            );

            filterChain.doFilter(request, response);
            return;
        }

        // ==========================================
        // EXTRACT TOKEN
        // ==========================================

        String token = header.substring(7);

        try {

            // ==========================================
            // EXTRACT EMAIL FROM JWT
            // ==========================================

            String email =
                    jwtService.extractEmail(token);

            System.out.println(
                    "JWT FILTER: Token belongs to "
                            + email
            );

            // ==========================================
            // CREATE AUTHENTICATION
            // ==========================================

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            Collections.emptyList()
                    );

            // ==========================================
            // SET SECURITY CONTEXT
            // ==========================================

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            System.out.println(
                    "JWT FILTER: Authentication set"
            );

        } catch (Exception e) {

            System.out.println(
                    "JWT FILTER: Invalid token - "
                            + e.getMessage()
            );

            SecurityContextHolder
                    .clearContext();
        }

        // ==========================================
        // CONTINUE REQUEST
        // ==========================================

        filterChain.doFilter(request, response);
    }
}