package com.songlify.exceptions;

public class SingerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public SingerNotFoundException(String message) {
        super(message);
    }
}
