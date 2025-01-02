package org.karensystem.core.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDto {
    private HttpStatus status;
    private String errorCode;
    private String errorMessage;
    private String message;
    private String ErrorDetails ;
    private Object data;
    private boolean success;
}
