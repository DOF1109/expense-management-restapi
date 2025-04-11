package com.danielfrias.restapi.exceptions;

import com.danielfrias.restapi.io.ErrorObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Global exception handler for the REST API.
 * This class handles exceptions thrown by the application and returns a standardized error response.
 * @author Daniel Frias
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorObject handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.info("Throwing a error from global error handler");
        return ErrorObject.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timestamp(new Date())
                .errorCode("RESOURCE_NOT_FOUND")
                .build();
    }

}
