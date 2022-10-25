package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.Review;
import kr.co.kwonshzzang.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Long restaurantId, Review resource) {
        resource.setRestaurantId(restaurantId);
        Review review =  reviewRepository.save(resource);
        return review;
    }
}
