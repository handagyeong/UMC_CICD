package spring.umc.domain.review.converter;

import org.springframework.stereotype.Component; // <-- 1. 스프링 부품으로 등록!
import spring.umc.domain.review.dto.ReviewResponseDTO;
import spring.umc.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    // entity->dto 변환
    public ReviewResponseDTO.ReviewDto toReviewDto(Review review) {

        return ReviewResponseDTO.ReviewDto.builder()
                .reviewId(review.getId())
                .memberName(review.getMember().getName()) //
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }


    public List<ReviewResponseDTO.ReviewDto> toReviewDtoList(List<Review> reviewList) {

        return reviewList.stream()
                .map(this::toReviewDto)
                .collect(Collectors.toList());
    }
}