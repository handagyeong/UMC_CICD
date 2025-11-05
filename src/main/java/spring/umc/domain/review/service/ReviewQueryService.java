package spring.umc.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    public List<Review> searchReview(String query, String type) {

        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }
        if (type.equals("star")) {
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if (type.equals("both")) {

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(review.store.location.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 리턴
        return reviewList;
    }

//    @Override
//    public List<Review> searchReview(
//            Predicate predicate
//    ) {
//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
//        QReview review = QReview.review;
//
//        return queryFactory
//                .selectFrom(review)
//                .where(predicate)
//                .fetch();
//    }
//
//    public List<Review> queryTest(String name) {
//        // Q클래스 정의
//        QReview review = QReview.review;
//
//        // BooleanBuilder 정의
//        BooleanBuilder builder = new BooleanBuilder();
//
//        // BooleanBuilder 사용 (예: 가게명 포함 검색)
//        builder.and(review.store.name.contains(name));
//
//        // Repository 사용 & 결과 매핑
//        List<Review> reviewList = reviewRepository.searchReview(builder);
//        return reviewList;
//    }
}
