package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;


public class Show extends Command {
	private final CollectionManager collectionManager;

	public Show(Console console, CollectionManager collectionManager) {
		super("show", "вывести все элементы коллекции");
		this.collectionManager = collectionManager;
	}

	@Override
	public ExecutionResponse apply(String[] arguments) {
		if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Аргументов быть не должно");
		
		return new ExecutionResponse(collectionManager.toString());
	}
}