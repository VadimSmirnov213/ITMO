package classes;

public record Location(String name) {

    @Override
    public String toString() {
        return name; 
    }
}