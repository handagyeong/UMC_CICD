package spring.umc.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    public List<Review> searchReview(
        //Predicate predicate
        String type, String query
    ) {
        //Q클래스 정의
        QReview review = QReview.review;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        //동적쿼리: 검색조건
        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }
        if (type.equals("star")){
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {
            // &기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            //동적 쿼리
            builder.and(review.store.location.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }

        //Repository 사용, 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }

    public List<Review> searchMyReview(Long memberId, String type, String query) {

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();


        builder.and(review.member.id.eq(memberId));


        if (type != null) {
            String safeType = type.trim();
            String safeQuery = (query != null) ? query.trim() : ""; // 쿼리도 null 방어!

            if (safeType.equalsIgnoreCase("location")) {
                builder.and(review.store.name.contains(safeQuery));
            }

            if (safeType.equalsIgnoreCase("star")){
                if (!safeQuery.isEmpty()) {
                    int starValue = Integer.parseInt(safeQuery);
                    if (starValue == 5) {
                        builder.and(review.star.eq(5.0f));
                    } else {
                        float lowerBound = (float) starValue;
                        float upperBound = lowerBound + 1.0f;

                        builder.and(review.star.goe(lowerBound).and(review.star.lt(upperBound)));
                    }
                }
                }


            if (safeType.equalsIgnoreCase("both")) {
                if (!safeQuery.isEmpty() && safeQuery.contains("&")) {


                    String storeNamePart = safeQuery.split("&")[0].trim();
                    String starPart = safeQuery.split("&")[1].trim();


                    if (!storeNamePart.isEmpty()) {
                        builder.and(review.store.name.contains(storeNamePart));
                    }


                    if (!starPart.isEmpty()) {
                        int starValue = Integer.parseInt(starPart);
                        if (starValue == 5) {
                            builder.and(review.star.eq(5.0f));
                        } else {
                            float lowerBound = (float) starValue;
                            float upperBound = lowerBound + 1.0f;
                            builder.and(review.star.goe(lowerBound).and(review.star.lt(upperBound)));
                        }
                    }
                }
            }

        }


        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;
    }
}