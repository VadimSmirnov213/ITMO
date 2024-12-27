package enums;

public enum Cloud {
    CLOUD("облачных ");
    private final String type;
    

    Cloud(String type) {
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