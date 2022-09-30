package kr.co.kwonshzzang.eatgo.interfaces;

import kr.co.kwonshzzang.eatgo.application.RestaurantService;
import kr.co.kwonshzzang.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant detail(@PathVariable Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {
        restaurantService.addRestaurant(resource);
        URI location = new URI("/restaurants/" + resource.getId() );
        return ResponseEntity.created(location).body("{}");
    }
}
