package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;


public class Save extends Command {
	private final CollectionManager collectionManager;
	public Save(Console console, CollectionManager collectionManager) {
		super("save", "сохранить коллекцию в файл");
		this.collectionManager = collectionManager;
	}


	@Override
	public ExecutionResponse apply(String[] arguments) {
		if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!");
		
		collectionManager.saveCollection();
		return new ExecutionResponse(true, "");
	}
}