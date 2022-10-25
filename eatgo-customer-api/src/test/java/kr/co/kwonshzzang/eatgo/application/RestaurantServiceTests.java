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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestaurantServiceTests {
    @Autowired
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private MenuItemRepository menuItemRepository;

    @MockBean
    private ReviewRepository reviewRepository;

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
        when(menuItemRepository.findAllByRestaurantId(1004L))
                .thenReturn(Lists.newArrayList(MenuItem.builder().name("Kimchi").build()));
        when(reviewRepository.findAllByRestaurantId(1004L))
                .thenReturn(Lists.newArrayList(Review.builder().id(123L).name("kwonshzzang").score(3).description("Mat-it-da").build()));

        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        Review review = restaurant.getReviews().get(0);

        assertEquals(restaurant.getId(), 1004L);
        assertEquals(menuItem.getName(), "Kimchi");
        assertEquals(review.getName(), "kwonshzzang");
        assertEquals(review.getScore(), 3);
    }

    @Test
    void getRestaurantWithNotExisted() {
        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getRestaurant(404L));
    }

}