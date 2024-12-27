package enums;

public enum ExwNorm {
    NORM("НОРМАЛЬНЫЕ"),
    NOTNORM("НЕНОРМАЛЬНЫЕ");
    private final String type;
    

    ExwNorm(String type) {
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