package pokemons;
import attacks.Confide;
import attacks.Psychic;
import ru.ifmo.se.pokemon.*;


public class Igglybuff extends Pokemon{
    public Igglybuff(String name, int level){
        super(name, level);
        setType(Type.NORMAL, Type.FAIRY);
        setStats(90,30,15,40,20,15);
        setMove(new Psychic(), new Confide());
    }
}