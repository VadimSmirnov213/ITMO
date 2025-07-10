package models;

// import utility.Validatable;

// отличия между Double и double

public class Coordinates {
    private int x;
    private Double y;

    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public Double getY() { return y; }


    @Override
    public String toString() {
        return String.format("Coordinates{x=%d, y=%.2f}", x, y);
    }
}
