package models;

public class Car {
    private String name;
    private boolean cool;

    public Car(String name, boolean cool) {
        this.name = name;
        this.cool = cool;
    }

    public String getName() { return name; }
    public boolean isCool() { return cool; }

    @Override
    public String toString() {
        return String.format("Car{name='%s', cool=%b}", name == null ? "null" : name, cool);
    }
}