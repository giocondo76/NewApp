package com.nc.controller.response;

public class UsernameExistsException extends Exception {
    public UsernameExistsException(String message)
    {
        super(message);
    }
}
