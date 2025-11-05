package spring.umc.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import java.util.List;


@Repository //흠..예시랑다른뎅..
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final EntityManager em;

    @Override
    public List<Review> searchReview(
            Predicate predicate
    ){

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Review> searchMyReview(
            Predicate predicate
    ){

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
