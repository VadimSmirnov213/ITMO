package enums;

public enum Extra {
    EXTRA("излишнее");
    private final String type;
    

    Extra(String type) {
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