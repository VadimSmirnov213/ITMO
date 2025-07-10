package commands;

import utility.Console;
import managers.CollectionManager;

import java.time.LocalDate;
import utility.ExecutionResponse;


public class Info extends Command {
	private final CollectionManager collectionManager;

	public Info(Console console, CollectionManager collectionManager) {
		super("info", "вывести информацию о коллекции");
		this.collectionManager = collectionManager;
	}


	@Override
	public ExecutionResponse apply(String[] arguments) {
		if (!arguments[1].isEmpty()) 
			return new ExecutionResponse(false, "Аргументов быть не должно");
		
        LocalDate lastInitTime = collectionManager.getLastInitTime();
        String lastInitTimeString = (lastInitTime == null) ? "в данном запуске инициализации еще не было" :
                    lastInitTime.toString();
                
        LocalDate lastSaveTime = collectionManager.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "в данном запуске сохранения еще не было" :
                    lastSaveTime.toString();
		
		var s="Информация о коллекции:\n";
		s+=" Тип: " + collectionManager.getCollection().getClass().toString()+"\n";
		s+=" Количество элементов: " + collectionManager.getCollection().size()+"\n";
		s+=" Дата последнего сохранения: " + lastSaveTimeString+"\n";
		s+=" Дата последней инициализации: " + lastInitTimeString;
		return new ExecutionResponse(s);
	}
}