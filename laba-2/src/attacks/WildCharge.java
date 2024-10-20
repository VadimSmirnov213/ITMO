package attacks;
import ru.ifmo.se.pokemon.*;

public class WildCharge extends PhysicalMove{
    public WildCharge(){
        super(Type.ELECTRIC, 90,100);
    }
    protected String describe(){
        return "использует атаку WildCharge";
    }
    protected void applyOppDamage(Pokemon def, double damage){
        def.setMod(Stat.HP, (int) Math.round(damage * 0.25));
    }
}