package kr.co.kwonshzzang.eatgo.interfaces;

import kr.co.kwonshzzang.eatgo.application.RestaurantService;
import kr.co.kwonshzzang.eatgo.domain.MenuItem;
import kr.co.kwonshzzang.eatgo.domain.Restaurant;
import kr.co.kwonshzzang.eatgo.domain.Review;
import kr.co.kwonshzzang.eatgo.exception.RestaurantNotFoundException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class RestaurantControllerTests {
    @Autowired
    private RestaurantController restaurantController;

//    @Autowired
//    private RestaurantExceptionHandler restaurantExceptionHandler;

    @Autowired
    private WebApplicationContext was;

    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @BeforeEach
    void beforeEach() {
        mvc = MockMvcBuilders
//                .standaloneSetup(restaurantController)
//                .setControllerAdvice(restaurantExceptionHandler)
                .webAppContextSetup(was)
                .alwaysDo(print())
                .build();
    }

    @Test
    void list() throws Exception {
        when(restaurantService.getRestaurants())
                .thenReturn(Lists.newArrayList(Restaurant.builder()
                                .id(1004L)
                                .name("JOKER House")
                                .address("Seoul")
                                .build()));

        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"JOKER House\"")));
    }

    @Test
    void detailWithExisted() throws Exception {
        Restaurant restaurant = Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").build();
        restaurant.setMenuItems(Arrays.asList(MenuItem.builder().name("Kimchi").build()));
        Review review = Review.builder().name("JOKER").score(5).description("Great!!").build();
        restaurant.setReviews(Arrays.asList(review));


        when(restaurantService.getRestaurant(1004L)).thenReturn(restaurant);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
                .andExpect(content().string(containsString("Kimchi")))
                .andExpect(content().string(containsString("JOKER")))
                .andExpect(content().string(containsString("Great!!")));
    }

    @Test
    void detailWithNotExisted() throws Exception {
        when(restaurantService.getRestaurant(404L)).thenThrow(new RestaurantNotFoundException(404L));
        mvc.perform(MockMvcRequestBuilders.get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}