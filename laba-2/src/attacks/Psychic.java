package attacks;
import ru.ifmo.se.pokemon.*;

public class Psychic extends PhysicalMove{
    public Psychic(){
        super(Type.PSYCHIC, 85,100);
    }
    protected String describe(){
        return "использует атаку Psychic";
    }
    public void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.1) {
            p.setMod(Stat.DEFENSE, -1);
        }
    }
}