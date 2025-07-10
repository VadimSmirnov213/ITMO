package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;
import utility.ExecutionResponse;


public class Update extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2 || arguments[1].isEmpty())  
            return new ExecutionResponse(false, "Команда требует один аргумент: id");
        try {
            long id = Long.parseLong(arguments[1]); 
            HumanBeing oldHuman = collectionManager.getById(id);
            if (oldHuman == null) 
                return new ExecutionResponse(false, "Элемент с id " + id + " не найден");
            HumanBeing updatedHuman = console.readHumanBeing(id);
            oldHuman.updateFrom(updatedHuman); 
    
            return new ExecutionResponse(true, "Элемент с id " + id + " успешно обновлен");
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "id должен быть числом");
        }
    }
}