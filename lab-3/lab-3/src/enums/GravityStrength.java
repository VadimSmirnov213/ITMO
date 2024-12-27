package enums;

public enum GravityStrength {
    MORE("больше"),
    LESS("меньше");
    private String type;
    

    GravityStrength(String type) {
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