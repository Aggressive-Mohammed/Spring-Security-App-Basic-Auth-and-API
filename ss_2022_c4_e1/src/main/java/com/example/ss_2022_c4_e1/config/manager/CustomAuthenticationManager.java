// Import necessary packages
package com.example.ss_2022_c4_e1.config.manager;

import com.example.ss_2022_c4_e1.config.providers.ApiKeyProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

// Lombok's AllArgsConstructor annotation generates a constructor that accepts all final fields.
@AllArgsConstructor

// This class is a custom implementation of the AuthenticationManager interface provided by Spring Security.
public class CustomAuthenticationManager implements AuthenticationManager {

    // A final String field that holds the API key to be used for authentication.
    private final String key;

    // This method is called by the Spring Security framework to authenticate the user.
    // It takes an Authentication object, and returns an Authentication object after processing.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Create a new instance of the ApiKeyProvider class with the key provided.
        var provider = new ApiKeyProvider(key);

        // Check if the provider supports the authentication object's class.
        if(provider.supports(authentication.getClass())){

            // If the provider supports the authentication object's class, authenticate using the provider.
            provider.authenticate(authentication);
        }

        // Return the original authentication object.
        return authentication;
    }
}
