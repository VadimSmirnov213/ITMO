package attacks;
import ru.ifmo.se.pokemon.*;

public class RockSlide extends PhysicalMove{
    public RockSlide(){
        super(Type.ROCK, 75,90);
    }
    protected String describe(){
        return "использует атаку RockSlide";
    }
    public void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3) {
            Effect.flinch(p);
        }
    }
}