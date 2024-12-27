package classes;

public record CreatureObj(String name) {

    @Override
    public String toString() {
        return name; 
    }
}