package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.Review;
import kr.co.kwonshzzang.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Long restaurantId, Review resource) {
        resource.setRestaurantId(restaurantId);
        Review review =  reviewRepository.save(resource);
        return review;
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
