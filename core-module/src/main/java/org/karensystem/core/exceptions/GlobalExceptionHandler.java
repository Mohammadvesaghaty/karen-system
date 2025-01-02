package org.karensystem.core.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.karensystem.core.exceptions.config.MessagesConfig;
import org.karensystem.core.exceptions.dto.ErrorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @Autowired
    private MessagesConfig msg;

    public GlobalExceptionHandler(MessagesConfig msg) {
        this.msg = msg;
    }


    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException exception){

        String errorMessage = "";
        HttpStatus statusCode;
        if( exception.getCause() instanceof ConstraintViolationException)
        {
            statusCode = HttpStatus.BAD_REQUEST;
            errorMessage = msg.getConstraintViolationException();
        } else
        {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            errorMessage = exception.getMessage();
        }

        return ResponseEntity.status(statusCode).body(
                ErrorResponseDto.builder()
                        .status(statusCode)
                        .success(false)
                        .errorMessage(errorMessage)
                        .build()
        );
    }



    @ExceptionHandler(value = Exception.class)
    public ResponseEntity allExceptionHandler(Exception exception){

        String errorMessage = "";
        HttpStatus statusCode;
        if( exception instanceof AuthenticationException)
        {
            statusCode = HttpStatus.UNAUTHORIZED;
            errorMessage = "You are not authorized";
        } else
        {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            errorMessage = exception.getMessage();
        }

        return ResponseEntity.status(statusCode).body(
                ErrorResponseDto.builder()
                        .status(statusCode)
                        .success(false)
                        .errorMessage(errorMessage)
                        .message(errorMessage)
                        .build()
        );
    }
}
