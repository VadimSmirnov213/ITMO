import ru.rmntim.web.controller.ValidationController;
import ru.rmntim.web.HitCalculator;

public class TestLocal {
    public static void main(String[] args) {
        ValidationController validator = new ValidationController();
        HitCalculator calculator = new HitCalculator();
        
        System.out.println("=== Локальное тестирование ===");
        
        // Тестовые случаи
        String[] testCases = {
            "{\"x\": 10000.0, \"y\": -2.5, \"r\": 4.0}",  // X вне диапазона
            "{\"x\": 2.5, \"y\": -4, \"r\": 4}",          // Дробные значения
            "{\"x\": 2, \"y\": -3, \"r\": 4}",            // Граничные значения
            "{\"x\": -5, \"y\": 5, \"r\": 1}",            // Минимальные значения
            "{\"x\": 3, \"y\": -5, \"r\": 5}",            // Максимальные значения
            "{\"x\": 0, \"y\": 0, \"r\": 3}",             // Центр координат
            "{\"x\": \"abc\", \"y\": 2, \"r\": 3}",       // Неправильный тип X
            "{\"x\": 2, \"y\": 999, \"r\": 3}",           // Y вне диапазона
            "{\"x\": 2, \"y\": 2, \"r\": 0.5}"            // R вне диапазона
        };
        
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\n--- Тест " + (i + 1) + " ---");
            System.out.println("Входные данные: " + testCases[i]);
            
            try {
                // Тест валидации
                var validationResult = validator.validateAndCreatePoint(testCases[i]);
                System.out.println("Валидация: " + (validationResult.isValid() ? "ПРОШЛА" : "НЕ ПРОШЛА"));
                if (!validationResult.isValid()) {
                    System.out.println("Ошибка валидации: " + validationResult.getErrorMessage());
                    continue;
                }
                
                // Тест создания Point
                var point = validator.createPoint(testCases[i]);
                System.out.println("Point создан: " + point);
                
                // Тест вычислений
                boolean hitResult = calculator.calculate(point.getX(), point.getY(), point.getR());
                System.out.println("Результат попадания: " + (hitResult ? "ПОПАДАЕТ" : "НЕ ПОПАДАЕТ"));
                
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        
        System.out.println("\n=== Тест завершен ===");
    }
}

