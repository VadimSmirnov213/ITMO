package commands;

import utility.ExecutionResponse;

public abstract class Command {
    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Абстрактный метод apply
    public abstract ExecutionResponse apply(String[] arguments);

    // Новый метод execute, который делегирует вызов apply
    public ExecutionResponse execute(String[] arguments) {
        return apply(arguments); 
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
