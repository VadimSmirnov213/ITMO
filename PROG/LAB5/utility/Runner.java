package utility;

import managers.CommandManager;
import java.util.NoSuchElementException;

public class Runner {
    private Console console;
    private final CommandManager commandManager;

    public Runner(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    public void interactiveMode() { 
        try {
            ExecutionResponse commandStatus;
            String[] userCommand = {"", ""};
            
            while (true) {
                console.prompt();
                userCommand = (console.readln().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
                
                if (commandStatus.getMessage().equals("exit")) break;
                console.println(commandStatus.getMessage());
            }
        } catch (NoSuchElementException exception) {
            console.printError("Ввод не обнаружен");
        } catch (IllegalStateException exception) {
            console.printError("Неизвестная ошибка");
        }
    }

    private ExecutionResponse launchCommand(String[] userCommand) {
        if (userCommand[0].equals("")) return new ExecutionResponse("");
        var command = commandManager.getCommands().get(userCommand[0]);
        
        if (command == null) return new ExecutionResponse(false, "Команда '" + userCommand[0] + "' не найдена.");
        
        switch (userCommand[0]) {
            default -> { return command.apply(userCommand); }
        }
    }
}
