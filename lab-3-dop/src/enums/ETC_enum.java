package enums;

public enum ETC_enum {
    LESSN("уменьшению"),
    LESS("меньшее"),
    MORE("усиленное");
    private final String type;
    

    ETC_enum(String type) {
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