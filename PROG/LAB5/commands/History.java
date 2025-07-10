package commands;

import managers.CommandManager;
import utility.Console;
import utility.ExecutionResponse;
import java.util.List;
import java.util.stream.Collectors;


public class History extends Command {
	private final CommandManager commandManager;

	public History(Console console, CommandManager commandManager) {
		super("history", "вывести последние 9 команд");
		this.commandManager = commandManager;
	}

	@Override
	public ExecutionResponse apply(String[] arguments) {
		if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Аргументов быть не должно");
		
		List<String> history = commandManager.getCommandHistory();
        int startIndex = Math.max(0, history.size() - 9);
        
        return new ExecutionResponse(history.subList(startIndex, history.size())
                .stream()
                .map(command -> " " + command)
                .collect(Collectors.joining("\n")));
	}
}