package attacks;
import ru.ifmo.se.pokemon.*;

public class DoubleEdge extends PhysicalMove{
    public DoubleEdge(){
        super(Type.NORMAL, 120,100);
    }
    protected String describe(){
        return "использует атаку DoubleEdge";
    }
    protected void applyOppDamage(Pokemon def, double damage){
        def.setMod(Stat.HP, (int) Math.round(damage * 0.33));
    }
}