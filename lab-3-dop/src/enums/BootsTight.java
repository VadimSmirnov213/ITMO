package enums;

public enum BootsTight {
    EASY("полегче"),
    TIGHT("потуже");
    private final String type;
    

    BootsTight(String type) {
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