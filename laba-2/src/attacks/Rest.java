package attacks;
import ru.ifmo.se.pokemon.*;

public class Rest extends PhysicalMove{
    public Rest(){
        super(Type.PSYCHIC, 0,0);
    }
    protected String describe(){
        return "использует атаку Rest";
    }
    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setCondition(new Effect().condition(Status.SLEEP).turns(2));
        pokemon.restore();
    }
}
