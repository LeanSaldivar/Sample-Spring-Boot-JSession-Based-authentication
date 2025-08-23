package com.lean.csrf.exception.custom;

public class PasswordsDoesNotMatchException extends RuntimeException {
    public PasswordsDoesNotMatchException(String message) {
        super(message);
    }
}
