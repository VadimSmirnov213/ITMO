package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

import models.HumanBeing;
import utility.LocalDateAdapter;
import utility.Console;


public class DumpManager {
    private final Gson gson = new GsonBuilder()
        .setPrettyPrinting() // для json
        .serializeNulls()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();

    private final String fileName;
    private final Console console;

    public DumpManager(String fileName, Console console) {
        if (!(new File(fileName).exists())) {
            fileName = "../" + fileName;
        }
        this.fileName = fileName;
        this.console = console;
    }

    public void writeCollection(Collection<HumanBeing> collection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(gson.toJson(collection));
            writer.flush();
            console.println("Коллекция успешно сохранена в файл!");
        } catch (IOException exception) {
            console.printError("Загрузочный файл не может быть открыт!");
        }
    }


    public Collection<HumanBeing> readCollection() {
        if (fileName != null && !fileName.isEmpty()) {
            try (Scanner scanner = new Scanner(new File(fileName))) {
                StringBuilder jsonString = new StringBuilder();

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.length() == 0) {
                    jsonString = new StringBuilder("[]");
                }

                var collectionType = new TypeToken<TreeSet<HumanBeing>>() {}.getType();
                TreeSet<HumanBeing> collection = gson.fromJson(jsonString.toString(), collectionType);

                console.println("Коллекция успешно загружена!");
                return collection;

            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (JsonParseException exception) {
                console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new TreeSet<>();
    }
}