package com.danielfrias.restapi.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ErrorObject is a class that represents the structure of an error response.
 * It contains information about the error, including the status code, message,
 * timestamp, and error code.
 * @author Daniel Frias
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;
    private String errorCode;
}
