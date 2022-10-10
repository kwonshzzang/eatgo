package kr.co.kwonshzzang.eatgo.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id) {
        super(("Could Not find restaurant " + id));
    }
}
