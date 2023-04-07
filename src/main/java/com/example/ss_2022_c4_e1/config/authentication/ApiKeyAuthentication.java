// Import necessary packages
package com.example.ss_2022_c4_e1.config.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

// This class represents an API key-based Authentication object, which is used for authenticating users using API keys.
//@RequiredArgsConstructor
public class ApiKeyAuthentication implements Authentication {

    // A final String field that holds the API key to be used for authentication.
    private final String key;

    // A boolean field that holds whether the authentication has been successful or not.
    private boolean authenticated;

    // This method returns true if the authentication has been successful.
    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    // This method sets the authentication status to either true or false.
    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    // A constructor that takes the API key as an argument and sets it as the key field.
    public ApiKeyAuthentication(String key) {
        this.key = key;
    }

    // These methods return null since this authentication object doesn't have any GrantedAuthority, Credentials, or Details.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    // This method returns the key as the name of the authenticated user.
    @Override
    public String getName() {
        return key;
    }

    // This method returns true if the authenticated user can access the specified Subject.
    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }

    // This method returns the API key used for authentication.
    public String getKey() {
        return key;
    }
}
