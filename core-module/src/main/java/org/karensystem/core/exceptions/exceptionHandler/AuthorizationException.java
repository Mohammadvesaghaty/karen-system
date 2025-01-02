package org.karensystem.core.exceptions.exceptionHandler;

public class AuthorizationException extends IllegalArgumentException{
    public AuthorizationException(String message) {
        super(message);
    }
}
