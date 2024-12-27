package enums;

public enum UpsidePosition {
    UP("вверх"),
    DOWN("вниз");
    private final String type;

    UpsidePosition(String type) {
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
