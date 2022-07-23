package lt.henrix.caloriesapp.food.Exception;

public class PersonException extends RuntimeException{
    public PersonException() {
    }

    public PersonException(String message) {
        super(message);
    }

    public PersonException(String message, Throwable cause) {
        super(message, cause);
    }
}
