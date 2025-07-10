package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.ExecutionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater id", "удалить из коллекции все элементы, id которых больше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2 || arguments[1].trim().isEmpty()) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!");
        }

        try {
            long id = Long.parseLong(arguments[1].trim());

            List<HumanBeing> toRemove = collectionManager.getCollection().stream()
                .filter(h -> h.getId() > id)
                .collect(Collectors.toList());

            if (toRemove.isEmpty()) {
                return new ExecutionResponse(false, "Нет элементов с ID больше " + id);
            }

            toRemove.forEach(h -> collectionManager.remove(h.getId()));

            return new ExecutionResponse(true, "Удалено элементов: " + toRemove.size());
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "ID должен быть числом.");
        }
    }
}
