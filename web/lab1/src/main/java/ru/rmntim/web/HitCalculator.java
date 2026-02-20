package ru.rmntim.web;


public class HitCalculator {
    public boolean calculate(double x, double y, double r) {
        if (x < 0 && y < 0) {
            return false;
        }
        if (x > 0 && y > 0) {
            if ((x * x + y * y) > (r / 2) * (r / 2)) {
                return false;
            }
        }
        if (x < 0 && y > 0) {
            if ((x + y) < -r / 2) {
                return false;
            }
        }
        if (x > 0 && y < 0) {
            if (x <= r / 2 && y >= -r) {
                return true;
            }
            return false;
        }
        return true;
    }
}
