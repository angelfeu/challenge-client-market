package com.decrypto.challenge.config.interceptor.xclient.exception;

public class AuthForbiddenException extends RuntimeException {

    private String message;

    public AuthForbiddenException(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
