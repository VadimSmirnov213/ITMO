package commands;

import managers.CollectionManager;
import utility.ExecutionResponse;

public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        collectionManager.clear();
        return new ExecutionResponse(true, "Коллекция очищена.");
    }
}
