package classes;

import enums.UpsidePosition;
import enums.WalkTypes;
import enums.WeightlessnessTime;
import interfaces.Afraid;
import interfaces.Force;
import interfaces.GetWeight;
import interfaces.Imagine;

import java.util.Objects;

import enums.Clarification;
import enums.Fly;
import enums.State;
import interfaces.Jump;
import interfaces.Justice;
import enums.WeightType;



public class Neznaika extends Person implements Imagine, Afraid, Jump, Force, GetWeight, Justice {
    
    public Neznaika(String name) {
        super(name); // Вызов конструктора суперкласса
    }

    @Override
    public String getName() {
        return this.name; // Возвращает имя
    }

    @Override
    public void setName(String name) {
        this.name = name; // Устанавливает имя
    }

    public void avoid() {
        System.out.println(this.name + " избежал этого");
    }

    public void steps(Location name1) {
        System.out.println("Не дожидаясь ответа, " + this.name + " зашагал бодрым шагом по направлению к " +
        name1);
    }

    public void dontfeel() {
        System.out.println(this.name + " не почувствовал перемен в весе");
    }

    public void walkedAhead(WalkTypes types) {
        System.out.println(this.name + " шагал " + types.Type());
    }

    public void forget() {
        System.out.println(this.name + " успел отвыкнуть от тяжести");
    }

    public void weightlessness(WeightlessnessTime time) {
        System.out.println(this.name + " провёл " + time.Type() + " время в состоянии невесомости.");
    }

    public String upsideDown(UpsidePosition str1) {
        return " висит " + str1 + " головой";
    }

    public void upsideDown1(UpsidePosition str2) {
        System.out.println("Всё казалось для " + this.name + " " + str2 + " тормашками.");
    }

    public String afraid_1() {
        return this.name + " опасался";
    }

    public void strong() {
        System.out.println(this.name + " был " + Clarification.VERY + " " + State.STRONG);
    }   

    public void thinner_than(Ponchik name) {
        System.out.println(this.name + " был не такой " + State.FAT + ", как " + name);
    } 

    @Override
    public void imagine(Neznaika name1, Location name2, SpaceBody name3, SpaceBody name4, CelestialBody name5, SpaceBoots name6) {
        System.out.println(name1 + " мерещилось, будно " + name2 + " вверху, а " + name3 + " со всеми " + name4 +  " и " + name5 +
        " внизу, и сам " + name1 + this.upsideDown(UpsidePosition.DOWN) + ", прицепившись к " + name2 + name6.upsideDown() 
        + ", которые были у него на ногах.");
    } // this.upsideDown(UpsidePosition.DOWN)

    @Override
    public void afraid(Neznaika name1, SpaceBoots name2, Location name3, CelestialBody name4) {
        System.out.println("В такие моменты " + name1.afraid_1() + ", что вот-вот выскользнет из своих " + name2 + " и полетит в "
        + name3 + " " + Fly.DOWN + " головой, а " + name2 + " остануться на " + name4 + ".");
    }

    @Override
    public void jump(CelestialBody name2, CreatureObj name3, CreatureObj name4) {
        System.out.println("Во всяком случае, " + this.name + " не прыгал по " + name2 + " словно какие-нибудь там " + name3 + " или "
        + name4 + ", а ходили нормально.");
    }

    @Override
    public void force(Person name1, SpaceBoots name2) {
        System.out.println("Это заставляло " + name1 + " поминутно хвататься руками за голеница " + name2 + " и потуже натягивать их на ноги.");
    }

    @Override
    public void getweight(CelestialBody name2, CelestialBody name3) {
        System.out.println("Тот вес, который " + this.name + " приобрел на " + name2 
        + ", показался ему самым " + WeightType.NORMAL + ", самым " + WeightType.GOOD + " весом, который " + this.name + " имел на " + name3);
    }

    @Override
    public void justice() {
        System.out.println("Справедливость требует отметить, что у "+ this.name + 
        " вовсе не было таких болезненных ощущений");
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean equals(Object name_1) {
        if (this == name_1) return true;
        if (name_1 == null || getClass() != name_1.getClass()) return false;
        Neznaika Neznaika = (Neznaika) name_1;
        return Objects.equals(this.name, Neznaika.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}