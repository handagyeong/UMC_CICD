package spring.umc.domain.review.exception.code; // <- 리뷰 도메인 패키지에!

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import spring.umc.global.apiPayload.code.BaseErrorCode; // <- ★★★

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode { // <- ★★★ BaseErrorCode 구현!


    REVIEW_SEARCH_TYPE_INVALID(HttpStatus.BAD_REQUEST, "REVIEW4001", "유효하지 않은 검색 타입입니다. ('location', 'star', 'both'만 가능)"),
    REVIEW_STAR_QUERY_NOT_NUMBER(HttpStatus.BAD_REQUEST, "REVIEW4002", "별점은 숫자 형식이어야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}