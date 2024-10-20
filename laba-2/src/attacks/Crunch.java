package attacks;
import ru.ifmo.se.pokemon.*;

public class Crunch extends PhysicalMove{
    public Crunch(){
        super(Type.DARK, 80,100);
    }
    protected String describe(){
        return "использует атаку Crunch";
    }
    public void applyOppEffects (Pokemon p) {
        if (Math.random() <= 0.2) {
            p.setMod(Stat.DEFENSE, -1);
        }
    }
}