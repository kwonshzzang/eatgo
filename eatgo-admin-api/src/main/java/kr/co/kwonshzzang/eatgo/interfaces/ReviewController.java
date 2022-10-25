package kr.co.kwonshzzang.eatgo.interfaces;

import kr.co.kwonshzzang.eatgo.application.ReviewService;
import kr.co.kwonshzzang.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;


    @GetMapping("/reviews")
    public List<Review> list() {
        return reviewService.getReviews();
    }

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(@PathVariable Long restaurantId, @Valid @RequestBody Review resource) throws URISyntaxException {
        Review newReview = reviewService.addReview(restaurantId, resource);
        return ResponseEntity.created(new URI("/restaurants/" + restaurantId + "/reviews/" + newReview.getId()))
                .body("{}");
    }
}
