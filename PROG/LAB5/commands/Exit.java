package commands;

import utility.Console;
import utility.ExecutionResponse;


public class Exit extends Command {
	public Exit(Console console) {
		super("exit", "завершить программу (без сохранения в файл)");
	}

	@Override
	public ExecutionResponse apply(String[] arguments) {
		if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!");
		
		return new ExecutionResponse("exit");
	}
}