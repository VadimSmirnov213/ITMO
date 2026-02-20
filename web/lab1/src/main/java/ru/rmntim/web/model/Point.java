package ru.rmntim.web.model;


public class Point {
    private final double x;
    private final double y;
    private final double r;
    
    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
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
    
    @Override
    public String toString() {
        return String.format("Point{x=%.6f, y=%.6f, r=%.6f}", x, y, r);
    }
}
