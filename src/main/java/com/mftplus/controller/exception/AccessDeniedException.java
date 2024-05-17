package com.mftplus.controller.exception;

public class AccessDeniedException extends Throwable {
    public AccessDeniedException(String message) {
        super(message);
    }
}
