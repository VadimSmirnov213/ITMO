package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;
import utility.ExecutionResponse;

public class Remove extends Command {
    private final CollectionManager collectionManager;
    public Remove(Console console, CollectionManager collectionManager) {
        super("remove id", "удалить элемент из коллекции по id");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2 || arguments[1].trim().isEmpty()) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!");
        }
        try {
            long id = Long.parseLong(arguments[1].trim());
            HumanBeing human = collectionManager.byId(id);
            if (human == null) {
                return new ExecutionResponse(false, "Элемент с ID " + id + " не найден в коллекции.");
            }
            collectionManager.remove(id);
            return new ExecutionResponse(true, "Элемент с ID " + id + " удален.");
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "ID должен быть числом.");
        }
    }
}
