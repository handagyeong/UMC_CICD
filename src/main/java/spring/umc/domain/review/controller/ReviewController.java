package spring.umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.review.dto.ReviewRequestDto;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.service.ReviewService;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 생성
     */
    @PostMapping
    public Review createReview(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestBody ReviewRequestDto.CreateReviewDto request) {

        return reviewService.createReview(
                memberId,
                storeId,
                request.getContent(),
                request.getScore(),
                request.getImageUrl()
        );
    }

    /**
     * 리뷰 동적 검색 (QueryDSL)
     */
    @GetMapping("/search")
    public Page<Review> searchReviews(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer minScore,
            @PageableDefault(size = 10) Pageable pageable) {

        return reviewService.searchReviews(memberId, storeId, minScore, pageable);
    }
}