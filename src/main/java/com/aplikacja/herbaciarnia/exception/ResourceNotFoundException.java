package com.aplikacja.herbaciarnia.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String s) {

        super("Resource not found" );
    }
}
