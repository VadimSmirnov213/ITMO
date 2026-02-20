package org.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PointDTO {
    private double x;
    private double y;
    private double r;
    private boolean hit;
    private String timestamp;
    private long executionTime;

    public PointDTO(double x, double y, double r, boolean hit, long executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.executionTime = executionTime;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isHit() {
        return hit;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return String.format("PointDTO{x=%.2f, y=%.2f, r=%.2f, hit=%s, time=%s}", 
        x, y, r, hit, timestamp);
    }
}
