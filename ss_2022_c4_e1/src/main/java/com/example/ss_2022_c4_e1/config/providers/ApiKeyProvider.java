// Import necessary packages
package com.example.ss_2022_c4_e1.config.providers;

import com.example.ss_2022_c4_e1.config.authentication.ApiKeyAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

// Lombok's AllArgsConstructor annotation generates a constructor that accepts all final fields.
@AllArgsConstructor

// This class is a custom implementation of the AuthenticationProvider interface provided by Spring Security.
public class ApiKeyProvider implements AuthenticationProvider {

    // A final String field that holds the API key to be used for authentication.
    private final String key;

    // This method is called by the Spring Security framework to authenticate the user.
    // It takes an Authentication object, and returns an Authentication object after processing.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Cast the Authentication object to ApiKeyAuthentication.
        ApiKeyAuthentication auth = (ApiKeyAuthentication) authentication;

        // Check if the API key in the authentication object matches the key provided to this provider.
        if (key.equals(auth.getKey())){

            // If the API key is correct, set the authentication object as authenticated and return it.
            auth.setAuthenticated(true);
            return auth;
        }

        // If the API key is incorrect, throw a BadCredentialsException.
        throw new BadCredentialsException(":(");
    }

    // This method returns true if the provider supports the given authentication class.
    @Override
    public boolean supports(Class<?> authentication) {

        // Check if the authentication class is the same as ApiKeyAuthentication.
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
