package enums;

public enum WeightType {
    NORMAL("нормальным"),
    GOOD("самым обыкновенным");
    private final String type;
    

    WeightType(String type) {
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