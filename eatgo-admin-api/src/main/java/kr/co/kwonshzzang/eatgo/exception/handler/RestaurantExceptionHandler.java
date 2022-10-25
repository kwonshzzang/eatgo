package kr.co.kwonshzzang.eatgo.exception.handler;

import kr.co.kwonshzzang.eatgo.exception.RestaurantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class RestaurantExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RestaurantNotFoundException.class)
    public String handleRestaurantNotFoundException() {
        return "{}";
    }
}
