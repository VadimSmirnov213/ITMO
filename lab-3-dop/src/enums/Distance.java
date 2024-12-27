package enums;

public enum Distance {
    NOTFAR("Неподалёку"),
    FAR("далеко");
    private final String type;
    

    Distance(String type) {
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