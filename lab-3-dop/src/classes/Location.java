package classes;

import enums.Cloud;
import enums.Distance;
import enums.Light;
import interfaces.MountainUp;
import interfaces.Road;

public class Location implements MountainUp, Road {
    public String name;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name; 
    }

    public void light(Light name1) {
        System.out.println(this.name + " была " + name1);
    }

    @Override
    public void mountain_up(Location name1, Location name3) {
        System.out.println(Distance.NOTFAR + " от скопления " + Cloud.CLOUD + name1 + " возвышалась одинокая " + name3 + " в виде темного конуса или пирамида.");
    }

    @Override
    public void road(Location name1) {
        System.out.println("От подножия " + this.name + " к " + name1 + ", на которой опустилась ракета, словно тоненький лучпротянулась " +
        this.getName());
    }

    @Override
    public String toString(){
        return this.name;
    }
}