package classes;

//import enums.UpsidePosition;

public class SpaceBoots {
    public String name;

    public SpaceBoots(String name) {
        this.name = name;
    }

    public String getName() {
        return name; // Возвращает имя
    }

    public String upsideDown() {
        return " с использованием подошвы " + this.name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}