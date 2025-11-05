package spring.umc.domain.review.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.store.entity.Store;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    // 1. 쿼리 메서드

    /**
     * 특정 가게(Store)의 모든 리뷰 찾기 (페이징, 최신순)
     */
    Page<Review> findByStoreOrderByCreatedAtDesc(Store store, Pageable pageable);

    /**
     * 특정 사용자(Member)가 작성한 모든 리뷰 찾기
     */
    List<Review> findByMember(Member member);

    // 2. @Query 어노테이션

    /**
     * 특정 사용자(memberId)가 작성한 리뷰를 최신순으로 정렬 (페이징)
     */
    @Query("SELECT r FROM Review r WHERE r.member.memberId = :memberId ORDER BY r.createdAt DESC")
    Page<Review> findAllByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    /**
     * 특정 가게(storeId)의 평균 평점(score) 계산하기
     */
    @Query("SELECT AVG(r.score) FROM Review r WHERE r.store.storeId = :storeId")
    Optional<Double> getAverageScoreByStoreId(@Param("storeId") Long storeId);
}