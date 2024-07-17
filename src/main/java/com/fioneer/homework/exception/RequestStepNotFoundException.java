package com.fioneer.homework.exception;

public class RequestStepNotFoundException extends RuntimeException {
    public RequestStepNotFoundException(String id) {
        super(String.format("Request step with ID %s not found.", id));
    }
}
