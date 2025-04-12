package com.danielfrias.restapi.exceptions;

/**
 * Custom exception class to handle resource not found scenarios.
 * This exception is thrown when a requested resource is not found in the system.
 * @author Daniel Frias
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
