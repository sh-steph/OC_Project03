package com.openclassrooms.occhatop.exceptions;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException (Long id) {
        super("The following user id doesn't exist : " + id);
    }
}
