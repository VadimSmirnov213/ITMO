package attacks;
import ru.ifmo.se.pokemon.*;

public class Harden extends StatusMove{
    public Harden(){
        super(Type.NORMAL, 0,0);
    }
    protected String describe(){
        return "использует атаку Harden";
    }
    public void applyOppEffects (Pokemon p) {
        p.setMod(Stat.DEFENSE, -1);
    }
}