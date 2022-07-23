package lt.henrix.caloriesapp.food.Exception;


public class FoodException extends RuntimeException {
    public FoodException() {
    }

    public FoodException(String message) {
        super(message);
    }

    public FoodException(String message, Throwable cause) {
        super(message, cause);
    }
}
