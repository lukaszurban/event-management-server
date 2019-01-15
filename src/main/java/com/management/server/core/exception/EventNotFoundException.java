package com.management.server.core.exception;

import lombok.NoArgsConstructor;

/**
 * Created by Łukasz on 14.01.2019
 */
@NoArgsConstructor
public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(String message) {
        super(message);
    }
}
