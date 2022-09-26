package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.MenuItem;
import kr.co.kwonshzzang.eatgo.domain.MenuItemRepository;
import kr.co.kwonshzzang.eatgo.domain.Restaurant;
import kr.co.kwonshzzang.eatgo.domain.RestaurantRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestaurantServiceTests {
    @Autowired
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private MenuItemRepository menuItemRepository;

    @Test
    void getRestaurants() {
        when(restaurantRepository.findAll())
                .thenReturn(Lists.newArrayList(new Restaurant(1004L, "Bob zip", "Seoul"),
                                                          new Restaurant(2020L, "Cyber Food", "Busan")));

        List<Restaurant> restaurants = restaurantService.getRestaurants();

        assertEquals(restaurants.size(), 2);
        assertEquals(restaurants.get(0).getId(), 1004L);
    }

    @Test
    void getRestaurant() {
        when(restaurantRepository.findById(1004L)).thenReturn(new Restaurant(1004L, "Bob zip", "Seoul"));
        when(menuItemRepository.findAllByRestaurantId(1004L)).thenReturn(Lists.newArrayList(new MenuItem("Kimchi")));

        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertEquals(restaurant.getId(), 1004L);
        assertEquals(menuItem.getName(), "Kimchi");
    }

    @Test
    void addRestaurant() {
        when(restaurantRepository.save(new Restaurant("BeRyong", "Busan")))
                .thenReturn(new Restaurant(1234L, "BeRyong", "Busan"));

        Restaurant created = restaurantService.addRestaurant(new Restaurant( "BeRyong", "Busan"));

        assertEquals(created.getId(), 1234L);
    }


}