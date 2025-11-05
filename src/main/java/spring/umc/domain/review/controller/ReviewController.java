package spring.umc.domain.review.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.service.ReviewQueryService;


import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.ReviewResponseDTO;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewConverter reviewConverter;

    @GetMapping("/reviews/search")
    public List<ReviewResponseDTO.ReviewDto> searchReview(
            @RequestParam String type,
            @RequestParam String query
    ){
        List<Review> reviewList = reviewQueryService.searchReview(type, query);

        List<ReviewResponseDTO.ReviewDto> dtoList = reviewConverter.toReviewDtoList(reviewList);

        return dtoList;
    }

    @GetMapping("/reviews/my")
    public List<ReviewResponseDTO.ReviewDto> searchMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String query
    ){
        List<Review> reviewList = reviewQueryService.searchMyReview(memberId, type,query);
        List<ReviewResponseDTO.ReviewDto> dtoList = reviewConverter.toReviewDtoList(reviewList);


        return dtoList;
    }
}