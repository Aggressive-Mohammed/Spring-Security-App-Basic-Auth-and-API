package com.example.ss_2022_c4_e1.config.filters;

import com.example.ss_2022_c4_e1.config.authentication.ApiKeyAuthentication;
import com.example.ss_2022_c4_e1.config.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

@AllArgsConstructor // constructor that takes the key parameter
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CustomAuthenticationManager manager = new CustomAuthenticationManager(key); // creates a new authentication manager using the key provided in the constructor

        var requestKey = request.getHeader("x-api-key"); // get the value of the "x-api-key" header from the request

        if ("null".equals(requestKey) || requestKey==null){ // if the header is null or equal to the string "null"
            filterChain.doFilter(request, response); // allow the request to pass through the filter chain without any authentication

        }
        var auth = new ApiKeyAuthentication(requestKey); // create a new instance of the ApiKeyAuthentication class using the value of the "x-api-key" header as the authentication key

        try {
            var a = manager.authenticate(auth); // authenticate the request using the custom authentication manager
            if (a.isAuthenticated()) { // if the authentication is successful
                SecurityContextHolder.getContext().setAuthentication(a); // set the authentication object in the security context holder
                filterChain.doFilter(request, response); // allow the request to pass through the filter chain
            }else { // if the authentication fails
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // set the response status to unauthorized

            }

        } catch (AuthenticationException e) { // if an authentication exception occurs
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // set the response status to unauthorized
        }
    }
}
