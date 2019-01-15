package com.management.server.core.exception;

/**
 * Created by Łukasz on 14.01.2019
 */
public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}
