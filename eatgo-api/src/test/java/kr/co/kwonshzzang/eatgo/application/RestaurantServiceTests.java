package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.MenuItem;
import kr.co.kwonshzzang.eatgo.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantServiceTests {
    @Autowired
    private RestaurantService restaurantService;

    @Test
    void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        assertEquals(restaurants.size(), 2);
        assertEquals(restaurants.get(0).getId(), 1004L);
    }

    @Test
    void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertEquals(restaurant.getId(), 1004L);
        assertEquals(menuItem.getName(), "Kimchi");
    }

}