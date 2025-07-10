package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;
import utility.ExecutionResponse;

public class Remove_by_is extends Command {
    private final CollectionManager collectionManager;

    public Remove_by_is(Console console, CollectionManager collectionManager) {
        super("remove_by_speed", "удалить из коллекции один элементы, значение поля impactSpeed которого эквивалентно заданному");
        this.collectionManager = collectionManager;
    }


    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty()) 
            return new ExecutionResponse(false, "Команда требует аргумент: impactSpeed");
        
        try {
            Float targetSpeed = Float.parseFloat(arguments[1]);
            
            for (HumanBeing human : collectionManager.getCollection()) {
                if (targetSpeed.equals(human.getImpactSpeed())) {
                    collectionManager.removeById(human.getId());
                    return new ExecutionResponse("Элемент с impactSpeed = " + targetSpeed + " успешно удален");
                }
            }
            
            return new ExecutionResponse("Элемент с impactSpeed = " + targetSpeed + " не найден");
            
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "impactSpeed должен быть числом");
        }
    }
}