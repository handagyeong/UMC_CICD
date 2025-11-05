package spring.umc.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDTO {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDto {
        Long reviewId;      // 리뷰 ID
        String memberName;  // 작성자 이름 (Member 엔티티사용x)
        String content;     // 리뷰 내용
        Float star;         // 별점
        LocalDateTime createdAt; // 작성일
    }

    // (참고: 나중에 "리뷰 생성" API 만들 때 쓸 응답 DTO 예시)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDto {
        Long reviewId;
        LocalDateTime createdAt;
    }
}