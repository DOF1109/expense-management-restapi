package com.danielfrias.restapi.exceptions;

import com.danielfrias.restapi.io.ErrorObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Global exception handler for the REST API.
 * This class handles exceptions thrown by the application and returns a standardized error response.
 * @author Daniel Frias
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles ResourceNotFoundException.
     * @param ex the exception thrown
     * @return a standardized error response
     */
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

    /**
     * Handles validation errors.
     * @param ex the exception thrown
     * @param headers the HTTP headers
     * @param status the HTTP status code
     * @param request the web request
     * @return a ResponseEntity with the error details
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<String> errors = ex.getFieldErrors().stream()
                .map(field -> field.getDefaultMessage())
                .toList();
        Map<String, Object> errorResponse = Map.of(
                "statusCode", HttpStatus.BAD_REQUEST.value(),
                "message", errors,
                "timestamp", new Date(),
                "errorCode", "VALIDATION_FAILED"
        );
        return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
