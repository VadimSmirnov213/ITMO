package classes;

public record SpaceBody(String name) {

    @Override
    public String toString() {
        return name; 
    }
}