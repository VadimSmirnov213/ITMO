package enums;

public enum BodyType {
    UP("верхней"),
    DOWN("нижней");
    private final String type;
    

    BodyType(String type) {
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