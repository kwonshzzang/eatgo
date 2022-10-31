package kr.co.kwonshzzang.eatgo.interfaces;

import kr.co.kwonshzzang.eatgo.application.ReviewService;
import kr.co.kwonshzzang.eatgo.domain.Review;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    void list() throws Exception {
        when(reviewService.getReviews())
                .thenReturn(Lists.newArrayList(Review.builder().name("kwonshzzang").description("Cool!").build()));

        mvc.perform(MockMvcRequestBuilders.get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Cool!")));
   }
}