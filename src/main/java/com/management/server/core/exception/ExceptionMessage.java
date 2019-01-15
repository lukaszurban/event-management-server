package com.management.server.core.exception;

/**
 * Created by Łukasz on 14.01.2019
 */
public enum ExceptionMessage {
    BAD_TOKEN("Provided token is not valid"),
    BAD_CREDENTIALS("Provided credentials is not valid"),
    EXPIRED_TOKEN("Expired token");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
