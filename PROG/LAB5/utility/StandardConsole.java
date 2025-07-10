package utility;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.time.LocalDate;
import java.util.Scanner;

import models.*;

public class StandardConsole implements Console {
	private static final String P1 = "$ ";
	private static Scanner fileScanner = null;
	private static Scanner defScanner = new Scanner(System.in);

	public void print(Object obj) {
		System.out.print(obj);
	}

	public void println(Object obj) {
		System.out.println(obj);
	}

	public void printError(Object obj) {
		System.err.println("Error(err): " + obj);
		System.out.println("Error(out): " + obj);
	}

	public String readln() throws NoSuchElementException, IllegalStateException {
		return (fileScanner!=null?fileScanner:defScanner).nextLine();
	}

	public boolean isCanReadln() throws IllegalStateException {
		return (fileScanner!=null?fileScanner:defScanner).hasNextLine();
	}

	public void printTable(Object elementLeft, Object elementRight) {
		System.out.printf(" %-35s%-1s%n", elementLeft, elementRight);
	}


	public void prompt() {
		print(P1);
	}


	public String getPrompt() {
		return P1;
	}

//////////////// переключение ввода (файл/консоль)

	public void selectFileScanner(Scanner scanner) {
		this.fileScanner = scanner;
	}

	public void selectConsoleScanner() {
		this.fileScanner = null;
	}

	@Override
    public HumanBeing readHumanBeing(long id) {
        println("Введите name:");
        String name = readln();
        while (name.isEmpty()) {
            println("Ошибка: name не может быть пустым. Повторите ввод:");
            name = readln();
        }

////////////////////////////

        Coordinates coordinates;
        while (true) {
            try {
                println("Введите x:");
                int x = Integer.parseInt(readln());
                println("Введите y:");
                double y = Double.parseDouble(readln());
                coordinates = new Coordinates(x, y);
                break;
            } catch (NumberFormatException e) {
                println("Ошибка: x и y должны быть числами. Повторите ввод:");
            } catch (IllegalArgumentException e) {
                println("Ошибка: " + e.getMessage() + ". Повторите ввод:");
            }
        }

////////////////////////////

        println("realHero (true/false):");
        boolean realHero;
        while (true) {
            String input = readln().toLowerCase();
            if ("true".equals(input)) { realHero = true; break; }
            if ("false".equals(input)) { realHero = false; break; }
            println("Ошибка: введите true или false. Повторите ввод:");
        }

        println("hasToothpick (true/false/null):");
        Boolean hasToothpick;
        while (true) {
            String input = readln().toLowerCase();
            if (input.isEmpty()) { hasToothpick = null; break; }
            if ("true".equals(input)) { hasToothpick = true; break; }
            if ("false".equals(input)) { hasToothpick = false; break; }
            println("Ошибка: введите true или false. Повторите ввод:");
        }

        println("impactSpeed (число или пустая строка для null):");
        Float impactSpeed;
        while (true) {
            String input = readln();
            if (input.isEmpty()) { impactSpeed = null; break; }
            try {
                impactSpeed = Float.parseFloat(input);
                break;
            } catch (NumberFormatException e) {
                println("Ошибка: введите число или пустую строку. Повторите ввод:");
            }
        }

        println("weaponType");
        println("Доступные значения: " + Arrays.toString(WeaponType.values()));
        WeaponType weaponType;
        while (true) {
            String input = readln();
            if (input.isEmpty()) { weaponType = null; break; }
            try {
                weaponType = WeaponType.valueOf(input.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                println("Ошибка: введите одно из указанных значений или пустую строку. Повторите ввод:");
            }
        }
		
        println("mood");
        println("Доступные значения: " + Arrays.toString(Mood.values()));
        Mood mood;
        while (true) {
            String input = readln();
            try {
                mood = Mood.valueOf(input.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                println("Ошибка: введите одно из указанных значений. Повторите ввод:");
            }
        }

        println("car name (или пустая строка для null):");
        String carName = readln();
        println("car name (или пустая строка для null):");
        String carCool = readln();      
        Car car = new Car(carName, Boolean.parseBoolean(carCool));
		

		try {
            return new HumanBeing(id, name, coordinates,LocalDate.now() ,realHero, hasToothpick, impactSpeed, weaponType, mood, car);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ошибка создания объекта: " + e.getMessage());
        }
	}
}