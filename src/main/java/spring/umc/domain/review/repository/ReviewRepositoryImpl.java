package spring.umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.entity.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {

    private final EntityManager em;              // ✅ ReviewRepository 주입 금지

    @Override
    public List<Review> searchReview(Predicate predicate) {
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QReview r = QReview.review;

        return qf.selectFrom(r)
                .where(predicate)
                .fetch();
    }
}
