package models;
import java.time.LocalDate;


public class HumanBeing implements Comparable<HumanBeing> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private Boolean hasToothpick; //Поле может быть null
    private Float impactSpeed; //Максимальное значение поля: 336, Поле не может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null

    public HumanBeing(long id, String name, Coordinates coordinates, java.time.LocalDate creationDate, boolean realHero, Boolean hasToothpick, Float impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    @Override
    public int compareTo(HumanBeing other) {
        return Long.compare(this.id, other.id);
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public boolean isRealHero() {
        return realHero;
    }
    public Boolean getHasToothpick() {
        return hasToothpick;
    }
    public Float getImpactSpeed() {
        return impactSpeed;
    }
    public WeaponType getWeaponType() {
        return weaponType;
    }
    public Mood getMood() {
        return mood;
    }
    public Car getCar() {
        return car;
    }

    public void updateFrom(HumanBeing other) {
        this.name = other.name;
        this.coordinates = other.coordinates;
        this.realHero = other.realHero;
        this.hasToothpick = other.hasToothpick;
        this.impactSpeed = other.impactSpeed;
        this.weaponType = other.weaponType;
        this.mood = other.mood;
        this.car = other.car;
    }
    
    @Override
    public String toString() {
        return String.format("HumanBeing{id=%d, name='%s', coordinates=%s, " +
            "creationDate=%s, realHero=%b, hasToothpick=%s, impactSpeed=%f, weaponType=%s, " +
            "mood=%s, car=%s}", id, name, coordinates, creationDate, 
            realHero, hasToothpick, 
            impactSpeed == null ? "null" : impactSpeed,
            weaponType == null ? "null" : weaponType,
            mood == null ? "null" : mood, car);
    }
}