package pokemons;
import attacks.Harden;
import attacks.RockSlide;
import attacks.TakeDown;
import attacks.Thunderbolt;
import ru.ifmo.se.pokemon.*;


public class Bergmite extends Pokemon{
    public Bergmite(String name, int level){
        super(name, level);
        setType(Type.ICE);
        setStats(55,	69,	85,	32,	35,	28);
        setMove(new RockSlide(), new TakeDown(), new Harden());
    }
}