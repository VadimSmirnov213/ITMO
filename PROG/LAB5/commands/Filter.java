package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;
import utility.ExecutionResponse;


public class Filter extends Command {
    private final CollectionManager collectionManager;

    public Filter(Console console, CollectionManager collectionManager) {
        super("filter", "вывести элементы, значение поля impactSpeed которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty()) 
            return new ExecutionResponse(false, "Команда требует аргумент: impactSpeed");
        
        try {
            Float targetSpeed = Float.parseFloat(arguments[1]);
            StringBuilder result = new StringBuilder();
            boolean found = false;
            
            for (HumanBeing human : collectionManager.getCollection()) {
                if (targetSpeed.equals(human.getImpactSpeed())) {
                    result.append(human).append("\n");
                    found = true;
                }
            }
            
            if (!found) {
                return new ExecutionResponse("Элементы с impactSpeed = " + targetSpeed + " не найдены");
            }
            
            return new ExecutionResponse("Элементы с impactSpeed = " + targetSpeed + ":\n" + result.toString());
            
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "impactSpeed должен быть числом");
        }
    }
}