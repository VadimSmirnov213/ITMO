package pokemons;
import attacks.DoubleEdge;
import attacks.Rest;
import attacks.WildCharge;
import ru.ifmo.se.pokemon.*;
import attacks.Thunderbolt;

public class Victini extends Pokemon{
    public Victini(String name, int level){
        super(name, level);
        setType(Type.PSYCHIC, Type.FIRE);
        setStats(100, 100, 100, 100, 100, 100);
        setMove(new Thunderbolt(), new DoubleEdge(), new Rest(), new WildCharge());
    }
}