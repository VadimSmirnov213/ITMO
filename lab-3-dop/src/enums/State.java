package enums;

public enum State {
    STRONG("крепенький"),
    FAT("толстый");
    private final String type;
    

    State(String type) {
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