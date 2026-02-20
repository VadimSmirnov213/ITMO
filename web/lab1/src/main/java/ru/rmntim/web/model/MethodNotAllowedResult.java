package ru.rmntim.web.model;

import java.time.LocalDateTime;

public class MethodNotAllowedResult {
    private final LocalDateTime timestamp;
    private final String errorMessage;
    
    public MethodNotAllowedResult(String errorMessage) {
        this.timestamp = LocalDateTime.now();
        this.errorMessage = errorMessage;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
