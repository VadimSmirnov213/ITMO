package ru.rmntim.web.model;

import java.time.LocalDateTime;


public class HitResult {
    private final long executionTime;
    private final LocalDateTime timestamp;
    private final boolean result;
    
    public HitResult(long executionTime, LocalDateTime timestamp, boolean result) {
        this.executionTime = executionTime;
        this.timestamp = timestamp;
        this.result = result;
    }
    
    public long getExecutionTime() {
        return executionTime;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public boolean isResult() {
        return result;
    }
    
    @Override
    public String toString() {
        return String.format("HitResult{executionTime=%d, timestamp=%s, result=%b}", 
            executionTime, timestamp, result);
    }
}
