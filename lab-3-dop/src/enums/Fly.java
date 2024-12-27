package enums;

public enum Fly {
    UP("вверх"),
    DOWN("вниз");
    private final String type;
    

    Fly(String type) {
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