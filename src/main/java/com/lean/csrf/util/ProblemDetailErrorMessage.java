package com.lean.csrf.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class ProblemDetailErrorMessage {
     public ErrorApiResponse create(HttpStatus status, String title, String detail, int errorCode, List<String> messages, HttpServletRequest request) {
        return new ErrorApiResponse(
                status.value(),
                title,
                detail,
                errorCode,
                messages,
                URI.create(request.getRequestURI())
        );
    }

    public ErrorApiResponse create(HttpStatus status, String title, String detail, int errorCode, HttpServletRequest request) {
        return create(status, title, detail, errorCode, Collections.singletonList(detail), request);
    }
}

