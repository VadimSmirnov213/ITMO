package classes;


import java.util.Objects;

//import enums.WalkTypes;
import enums.AttractionStrength;
import enums.GravityStrength;
import exceptions.InvalidArgumentException;
import interfaces.Comparison;
import interfaces.ExactComparison;

public class CelestialBody implements Comparison, ExactComparison{
    public String name;
    public double gravity; ////////////////////

    public CelestialBody(String name, double gravity) throws InvalidArgumentException {
        this.name = name;
        setGravity(gravity);
    }

    public String getName() {
        return name; // Возвращает имя небесного тела
    }

    public double getGravity() {
        return gravity; // Возвращает гравитацию
    }

    public void setGravity(double gravity) throws InvalidArgumentException {
        if (gravity <= 0) {
            throw new InvalidArgumentException();
        }
        this.gravity = gravity;
    }    


    @Override
    public void comparison(CelestialBody name2, Person p1, Person p2) {
        if (this.gravity < name2.getGravity()) {
            System.out.println("Хотя " + this.name + " и притягивала " + p1.getName() + " и " + p2.getName() + " с " + AttractionStrength.WEAK + " силой, чем притягивала " + name2.getName());  // Хотя Луна и притягивала их с меньшей силой, чем притягивала Земля
        } else if (this.gravity > name2.getGravity()) {
            System.out.println("Хотя " + this.name + " и притягивала " + p1.getName() + " и " + p2.getName() + " с " + AttractionStrength.STRONG + " силой, чем притягивала " + name2.getName());  // Хотя Луна и притягивала их с меньшей силой, чем притягивала Земля
        } else {
            System.out.println(this.name + " и " + name2.getName() + " имеют равные силы притяжения.");  // Хотя Луна и притягивала их с меньшей силой, чем притягивала Земля
        }
    }
/// Некоторые объясняют это тем, что на Луне сила тяжести чуть ли не в шесть раз меньше, чем на Земле. 
    @Override
    public void exactcomparison(CelestialBody name1, CelestialBody name2) {
        if (name1.getGravity() < name2.getGravity()) {
            System.out.println("Некоторые объясняют это тем, что на " + name1.getName() + " сила тяжести чуть ли не в " + (int) (name2.getGravity() / name1.getGravity()) + " раз " + GravityStrength.LESS + ", чем на " + name2.getName());  
        } else if (name1.getGravity() > name2.getGravity()) {
            System.out.println("Некоторые объясняют это тем, что на " + name2.getName() + " сила тяжести чуть ли не в " + (int) (name1.getGravity() / name2.getGravity()) + " раз " + GravityStrength.LESS + ", чем на " + name1.getName());  
        } else {
            System.out.println(name1.getName() + " и " + name2.getName() + " имеют равные силы притяжения.");  // Хотя Луна и притягивала их с меньшей силой, чем притягивала Земля
        }
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean equals(Object name_1) {
        if (this == name_1) return true;
        if (name_1 == null || getClass() != name_1.getClass()) return false;
        CelestialBody body = (CelestialBody) name_1;
        return Objects.equals(this.name, body.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}