package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Comparator;
import java.util.Optional;

public class AddIfMin extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public AddIfMin(Console console, CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его impactSpeed меньше, чем у наименьшего элемента");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        Optional<HumanBeing> minElement = collectionManager.getCollection().stream()
                .min(Comparator.comparing(HumanBeing::getImpactSpeed));

        HumanBeing newHuman = console.readHumanBeing(collectionManager.generateId());


        if (minElement.isEmpty() || newHuman.getImpactSpeed() < minElement.get().getImpactSpeed()) {
            collectionManager.add(newHuman);
            return new ExecutionResponse(true, "Элемент успешно добавлен в коллекцию.");
        } else {
            return new ExecutionResponse(false, "Элемент не добавлен, так как его impactSpeed больше либо равен минимальному в коллекции.");
        }
    }
}
