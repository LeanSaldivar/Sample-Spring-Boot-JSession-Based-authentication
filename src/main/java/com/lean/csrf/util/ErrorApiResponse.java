package com.lean.csrf.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.util.List;

@Getter
@Setter
// This is useful to avoid including null fields in the JSON response
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorApiResponse extends ProblemDetail {
    private final List<String> messages;
    private final int code;

    public ErrorApiResponse(int status, String title, String detail, int code, List<String> messages, URI instance) {
        super(status);
        setTitle(title);
        setDetail(detail);
        setInstance(instance);
        this.code = code;
        this.messages = messages;
    }
}
