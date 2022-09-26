package kr.co.kwonshzzang.eatgo.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantRepositoryTests {
    private RestaurantRepository restaurantRepository;


    @Test
    void save() {
        restaurantRepository = new RestaurantRepositoryImpl();

        int oldCount = restaurantRepository.findAll().size();

        Restaurant restaurant = new Restaurant("BeRyong", "Seoul");
        restaurantRepository.save(restaurant);

        assertEquals(restaurant.getId(), 1234L);

        int newCount = restaurantRepository.findAll().size();

        assertEquals(newCount - oldCount, 1);
    }

}