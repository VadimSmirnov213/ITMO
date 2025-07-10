package commands;

import managers.CollectionManager;
import utility.Console;
import java.util.TreeSet;
import utility.ExecutionResponse;


public class Print extends Command {
    private final CollectionManager collectionManager;

    public Print(Console console, CollectionManager collectionManager) {
        super("print", "вывести значения поля impactSpeed в порядке возрастания");
        this.collectionManager = collectionManager;
    }


    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) 
            return new ExecutionResponse(false, "Аргументов быть не должно");
        
        var beNull = false;
        var ts = new TreeSet<Float>();
        for (var e : collectionManager.getCollection()) {
            if (e.getImpactSpeed() == null)
                beNull = true;
            else
                ts.add(e.getImpactSpeed());
        }
        
        var s = "";
        if (beNull)
            s = " null";
        for (var e : ts)
            s += " " + e;
            
        return new ExecutionResponse("Значения impactSpeed:" + s);
    }
}