package com.nc.controller.response;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message)
    {
        super(message);
    }
}