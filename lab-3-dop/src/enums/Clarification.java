package enums;

public enum Clarification {
    VERY("очень"),
    NOTVERY("не очень");
    private final String type;
    

    Clarification(String type) {
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