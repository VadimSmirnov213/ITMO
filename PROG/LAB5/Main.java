import commands.*;

import managers.CollectionManager;
import managers.CommandManager;
import managers.DumpManager;



import utility.StandardConsole;
import utility.Runner;

public class Main {
    public static void main(String[] args) {
        var console = new StandardConsole();
        
        String fileName = args.length > 0 ? args[0] : "data.json";
        
        var dumpManager = new DumpManager(fileName, console);
        var collectionManager = new CollectionManager(dumpManager);
        if (!collectionManager.loadCollection()) {
            console.printError("Ошибка загрузки коллекции");
            return; 
        }
        
        var commandManager = new CommandManager() {{
            register("help", new Help(console, this));
            register("execute", new Execute(console, this));
            register("show", new Show(console, collectionManager));
            register("info", new Info(console, collectionManager));
            register("clear", new Clear(collectionManager));
            register("add", new Add(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("update", new Update(console, collectionManager));
            register("remove", new Remove(console, collectionManager));
            register("history", new History(console, this));
            register("print", new Print(console, collectionManager));
            register("filter", new Filter(console, collectionManager));
            register("remove_by_is", new Remove_by_is(console, collectionManager));
            register("add_if_min", new AddIfMin(console, collectionManager));
            register("exit", new Exit(console));
            register("remove_greater", new RemoveGreater(collectionManager));
            register("add_if_min", new AddIfMin(console, collectionManager));
        }};
        
        new Runner(console, commandManager).interactiveMode();
    }
}