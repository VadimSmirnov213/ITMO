package pokemons;
import attacks.Confide;
import attacks.Pound;
import attacks.Psychic;
import ru.ifmo.se.pokemon.*;


public class Jigglypuff extends Igglybuff{
    public Jigglypuff(String name, int level){
        super(name, level);
        setType(Type.NORMAL, Type.FAIRY);
        setStats(115,45,20,45,25,20);
        setMove(new Pound());
    }
}