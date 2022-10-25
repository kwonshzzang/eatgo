package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.Review;
import kr.co.kwonshzzang.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
    void addReview() {
        Review review = Review.builder()
                .name("JoKER")
                .score(3)
                .description("Mat-it-da")
                .restaurantId(1L)
                .build();

        when(reviewRepository.save(any())).thenReturn(review);

        reviewService.addReview(1L, review);
        verify(reviewRepository).save(any());
    }

}