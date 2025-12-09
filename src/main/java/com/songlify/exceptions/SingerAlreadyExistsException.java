package com.songlify.exceptions;

public class SingerAlreadyExistsException extends RuntimeException {

    public SingerAlreadyExistsException(String message) {
        super(message);
    }
}
