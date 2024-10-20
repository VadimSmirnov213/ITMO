package pokemons;
import attacks.Crunch;
import attacks.Harden;
import attacks.RockSlide;
import attacks.TakeDown;
import ru.ifmo.se.pokemon.*;


public class Avalugg extends Bergmite{
    public Avalugg(String name, int level){
        super(name, level);
        setType(Type.ICE);
        setStats(95,117,184,44,46,28);
        setMove(new Crunch());
    }
}