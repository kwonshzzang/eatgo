package kr.co.kwonshzzang.eatgo.interfaces;

import kr.co.kwonshzzang.eatgo.application.ReviewService;
import kr.co.kwonshzzang.eatgo.domain.Review;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class ReviewControllerTests {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext was;

    @MockBean
    private ReviewService reviewService;

    @BeforeEach
    void beforeEach() {
        mvc = MockMvcBuilders
                .webAppContextSetup(was)
                .alwaysDo(print())
                .build();
    }

    @Test
    void createWithValidAttributes() throws Exception {
        when(reviewService.addReview(eq(1L), any()))
                .thenReturn(Review.builder().id(123L).name("kwonshzzang").score(3).description("Mat-it-da").build());

        mvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"kwonshzzang\",\"score\":3, \"description\":\"Mat-it-da\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1/reviews/123"));

        verify(reviewService).addReview(eq(1L), any());
    }

    @Test
    void createWithInvalidAttributes() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(eq(1L), any());
    }
}