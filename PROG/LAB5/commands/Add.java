package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;
import utility.ExecutionResponse;


public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) 
            return new ExecutionResponse(false, "Команда 'add' не принимает аргументы");
        
        long id = collectionManager.generateId();
        HumanBeing human = console.readHumanBeing(id);
        collectionManager.add(human);
        
        StringBuilder result = new StringBuilder();
        result.append("Элемент успешно добавлен в коллекцию\n");
        result.append("Добавленный элемент:\n");
        result.append(human.toString());
        
        return new ExecutionResponse(true, result.toString());
    }
}