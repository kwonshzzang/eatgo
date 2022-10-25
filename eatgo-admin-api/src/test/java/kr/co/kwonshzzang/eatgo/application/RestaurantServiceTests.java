package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.*;
import kr.co.kwonshzzang.eatgo.exception.RestaurantNotFoundException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestaurantServiceTests {
    @Autowired
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Test
    void getRestaurants() {
        when(restaurantRepository.findAll())
                .thenReturn(Lists.newArrayList(
                        Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").build(),
                        Restaurant.builder().id(2020L).name("Cyber Food").address("Busan").build()));

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        assertEquals(restaurants.size(), 2);
        assertEquals(restaurants.get(0).getId(), 1004L);
    }

    @Test
    void getRestaurantWithExisted() {
        when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build()));

        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertEquals(restaurant.getId(), 1004L);
        assertEquals(restaurant.getName(), "Bob zip");
        assertEquals(restaurant.getAddress(), "Seoul");
    }

    @Test
    void getRestaurantWithNotExisted() {
        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getRestaurant(404L));
    }

    @Test
    void addRestaurant() {
        Restaurant resource = Restaurant.builder().name("BeRyong").address("Busan").build();
        Restaurant restaurant =Restaurant.builder().id(1234L).name("BeRyong").address("Busan").build();

        when(restaurantRepository.save(resource)).thenReturn(restaurant);

        Restaurant created = restaurantService.addRestaurant(resource);
        assertEquals(created.getId(), 1234L);
    }

    @Test
    void updateRestaurant() {
        when(restaurantRepository.findById(1004L))
                .thenReturn(Optional.of(Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").build()));

        Restaurant updated = restaurantService.updateRestaurant(1004L, Restaurant.builder().name("Sool zip").address("Busan").build());
        assertEquals(updated.getName(), "Sool zip");
        assertEquals(updated.getAddress(), "Busan");

    }


}