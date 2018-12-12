package com.management.server.core.exception;

/**
 * Created by Łukasz on 03.12.2018
 */
public class EmailExistsException extends Throwable {
    public EmailExistsException(String message) {
        super(message);
    }
}
