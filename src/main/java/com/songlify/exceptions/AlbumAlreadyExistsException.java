package com.songlify.exceptions;

public class AlbumAlreadyExistsException extends RuntimeException {
   
    public AlbumAlreadyExistsException(String message) {
        super(message);
    }
}
