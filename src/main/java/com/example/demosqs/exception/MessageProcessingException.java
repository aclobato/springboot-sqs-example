package com.example.demosqs.exception;

public class MessageProcessingException extends RuntimeException {
    public MessageProcessingException(String message) {
        super(message);
    }
}
