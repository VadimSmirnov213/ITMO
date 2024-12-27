package classes;

public record BodyPart(String name) {

    @Override
    public String toString() {
        return name; 
    }
}