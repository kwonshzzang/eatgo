package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.Review;
import kr.co.kwonshzzang.eatgo.domain.ReviewRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReviewServiceTests {
    @Autowired
    private ReviewService reviewService;

    @MockBean
    private ReviewRepository reviewRepository;


    @Test
    void getReviews() {
        when(reviewRepository.findAll())
                .thenReturn(Lists.newArrayList(Review.builder().id(1L).name("kwonshzzang").score(5).description("Cool!").build()));

        List<Review> reviews = reviewService.getReviews();

        assertEquals(reviews.size(), 1);
        assertEquals(reviews.get(0).getId(), 1L);
        assertEquals(reviews.get(0).getName(), "kwonshzzang");
        assertEquals(reviews.get(0).getScore(), 5);
        assertEquals(reviews.get(0).getDescription(), "Cool!");
    }

}