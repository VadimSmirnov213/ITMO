package ru.rmntim.web.model;

import java.time.LocalDateTime;

public class NotFoundResult {
    private final LocalDateTime timestamp;
    private final String errorMessage;
    
    public NotFoundResult(String errorMessage) {
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
