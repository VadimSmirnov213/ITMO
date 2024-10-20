package attacks;
import ru.ifmo.se.pokemon.*;

public class TakeDown extends PhysicalMove{
    public TakeDown(){
        super(Type.NORMAL, 90,85);
    }
    protected String describe(){
        return "использует атаку TakeDown";
    }
    protected void applyOppDamage(Pokemon def, double damage){
        def.setMod(Stat.HP, (int) Math.round(damage * 0.25));
    }
}