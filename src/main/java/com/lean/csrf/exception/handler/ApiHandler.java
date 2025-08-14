package com.lean.csrf.exception.handler;

import com.lean.csrf.exception.custom.DuplicateResourceException;
import com.lean.csrf.exception.custom.ResourceNotFoundException;
import com.lean.csrf.util.ErrorApiResponse;
import com.lean.csrf.util.LoggerErrorMessage;
import com.lean.csrf.util.ProblemDetailErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        LoggerErrorMessage.logWarn(
                logger,
                ex.getMessage(),
                request);

        ErrorApiResponse errorDetail = ProblemDetailErrorMessage.create(
                HttpStatus.NOT_FOUND,
                "Resource Not Found. Please check the request parameters for invalid values or missing values. If the resource you are looking for does not exist, please contact the developer for assistance.",
                ex.getMessage(),
                404, // Example custom error code for this specific case
                request
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ProblemDetail> handleDuplicateResourceException(DuplicateResourceException ex, HttpServletRequest request) {
        LoggerErrorMessage.logWarn(
                logger,
                ex.getMessage(),
                request);

        ErrorApiResponse errorDetail = ProblemDetailErrorMessage.create(
                HttpStatus.NOT_FOUND,
                "Duplicate Resource or Unique Constraint Violation. Please check the request body for duplicate values.",
                ex.getMessage(),
                409, // Example custom error code for this specific case
                request
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }


}
