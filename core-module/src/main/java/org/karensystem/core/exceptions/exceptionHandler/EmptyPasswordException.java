package org.karensystem.core.exceptions.exceptionHandler;

public class EmptyPasswordException extends IllegalArgumentException{
    public EmptyPasswordException(String message) {
        super(message);
    }
}
