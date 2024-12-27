package enums;

public enum LegsPosition {
    UP("вверх"),
    DOWN("вниз");
    private final String type;
    

    LegsPosition(String type) {
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