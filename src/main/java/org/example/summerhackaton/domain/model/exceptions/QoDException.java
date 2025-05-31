package org.example.summerhackaton.domain.model.exceptions;

public class QoDException extends RuntimeException {
    public QoDException(String message, Throwable cause) {
        super(message, cause);
    }
}