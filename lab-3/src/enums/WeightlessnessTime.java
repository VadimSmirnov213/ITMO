package enums;

public enum WeightlessnessTime {
    LONG("долгое"),
    SHORT("короткое");
    private final String type;

    WeightlessnessTime(String type) {
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