package com.lean.csrf.exception.handler;


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
public class GlobalHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception ex, HttpServletRequest request) {
        LoggerErrorMessage.logError(
                logger,
                ex,
                request
        );

        ErrorApiResponse errorDetail = ProblemDetailErrorMessage.create(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error. Please contact the developer for assistance.",
                ex.getMessage(),
                500, // Example custom error code for this specific case
                request
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
