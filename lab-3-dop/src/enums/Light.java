package enums;

public enum Light {
    LG("светлая"),
    DARK("тёмная");
    private final String type;
    

    Light(String type) {
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