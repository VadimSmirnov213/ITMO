package exceptions;

public class InvalidValueException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Ошибка: значение недопустимо.";
    }
}