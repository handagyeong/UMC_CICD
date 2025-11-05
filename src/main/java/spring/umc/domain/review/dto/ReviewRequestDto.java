package spring.umc.domain.review.dto;

import lombok.Getter;

public class ReviewRequestDto {

    @Getter
    public static class CreateReviewDto {
        private String content;
        private Integer score;
        private String imageUrl;
    }
}