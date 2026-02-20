package ru.rmntim.web.model;
import java.time.LocalDateTime;


public class ValidationResult {
    private final boolean valid;
    private final String errorMessage;
    private final LocalDateTime timestamp;
    
    public ValidationResult(boolean valid, String errorMessage, LocalDateTime timestamp) {
        this.valid = valid;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }
    
    public static ValidationResult success() {
        return new ValidationResult(true, null, LocalDateTime.now());
    }
    
    public static ValidationResult error(String errorMessage) {
        return new ValidationResult(false, errorMessage, LocalDateTime.now());
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return String.format("ValidationResult{valid=%b, errorMessage='%s', timestamp=%s}", 
            valid, errorMessage, timestamp);
    }
}
