package enums;

public enum WalkTypes {
    AHEAD("впереди"),
    BEHIND("сзади");
    private final String type;

    WalkTypes(String type) {
        this.type = type;
    }

    public String Type() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}   
