package net.recipes.exception;


public class ValidationException extends RuntimeException {
    public ValidationException(String check) {
        super("Ошибка валидации" + check);
    }
}