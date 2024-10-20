package pokemons;
import attacks.Confide;
import attacks.DoubleEdge;
import attacks.Pound;
import attacks.Psychic;
import ru.ifmo.se.pokemon.*;


public class Wigglytuff extends Jigglypuff{
    public Wigglytuff(String name, int level){
        super(name, level);
        setType(Type.NORMAL, Type.FAIRY);
        setStats(140,70,45,85,50,45);
        setMove(new DoubleEdge());
    }
}