package commands;

import managers.CommandManager;
import utility.Console;
import utility.ExecutionResponse;

import java.util.stream.Collectors;


public class Help extends Command {
	private final CommandManager commandManager;

	public Help(Console console, CommandManager commandManager) {
		super("help", "вывести справку по командам");
		this.commandManager = commandManager;
	}

	@Override
	public ExecutionResponse apply(String[] arguments) {
		if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Аргументов быть не должно");
		
		return new ExecutionResponse(commandManager.getCommands().values().stream().map(command -> String.format(" %-25s%-1s%n", command.getName(), command.getDescription())).collect(Collectors.joining()));
	}
}