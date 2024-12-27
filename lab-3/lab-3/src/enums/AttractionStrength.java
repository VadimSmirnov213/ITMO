package enums;

public enum AttractionStrength {
    STRONG("большей"),
    WEAK("меньшей"),
    EQUAL("равные");
    private String type;
    

    AttractionStrength(String type) {
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