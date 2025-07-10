package commands;

import managers.CommandManager;
import utility.Console;
import utility.ExecutionResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Execute extends Command {
    private final Console console;
    private final CommandManager commandManager;
    private final List<String> executedScripts = new ArrayList<>();

    public Execute(Console console, CommandManager commandManager) {
        super("execute_script", "выполняет скрипт из файла");
        this.console = console;
        this.commandManager = commandManager;
    }

    @Override
    public ExecutionResponse apply(String[] userCommand) {
        if (userCommand.length < 2 || userCommand[1].isEmpty()) {
            return new ExecutionResponse(false, "Не указан файл скрипта. Использование: execute_script <filename>");
        }
        return executeScript(userCommand[1]);
    }

    private ExecutionResponse executeScript(String filename) {
        if (executedScripts.contains(filename)) {
            return new ExecutionResponse(false, "Обнаружена рекурсия! Скрипт " + filename + " уже выполняется");
        }
        executedScripts.add(filename);

        File scriptFile = new File(filename);
        if (!scriptFile.exists()) {
            return new ExecutionResponse(false, "Файл скрипта не найден: " + filename);
        }
        if (!scriptFile.canRead()) {
            return new ExecutionResponse(false, "Нет прав на чтение файла: " + filename);
        }

        try (Scanner scriptScanner = new Scanner(scriptFile)) {
            StringBuilder output = new StringBuilder();
            console.selectFileScanner(scriptScanner);

            while (scriptScanner.hasNextLine()) {
                String line = scriptScanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = (line + " ").split(" ", 2);
                String commandName = parts[0];
                String arguments = parts.length > 1 ? parts[1].trim() : "";

                Command command = commandManager.getCommands().get(commandName);
                if (command == null) {
                    output.append("Неизвестная команда: ").append(commandName).append("\n");
                    continue;
                }

                ExecutionResponse response = command.execute(new String[]{commandName, arguments});
                output.append(response.getMessage()).append("\n");

                if (!response.getExitCode() || commandName.equals("exit")) {
                    break;
                }
            }

            return new ExecutionResponse(true, output.toString());
        } catch (FileNotFoundException e) {
            return new ExecutionResponse(false, "Ошибка чтения файла скрипта");
        } finally {
            executedScripts.remove(filename);
            console.selectConsoleScanner();
        }
    }
}