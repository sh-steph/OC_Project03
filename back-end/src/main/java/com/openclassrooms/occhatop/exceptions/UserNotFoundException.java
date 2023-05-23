package com.openclassrooms.occhatop.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("The following user doesn't exist : " + email);
    }
}