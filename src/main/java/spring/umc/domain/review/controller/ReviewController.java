package spring.umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.service.ReviewQueryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ) {
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }
}