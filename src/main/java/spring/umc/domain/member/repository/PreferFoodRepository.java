package spring.umc.domain.member.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.FoodCategory;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.entity.PreferFood;

public interface PreferFoodRepository extends JpaRepository<PreferFood, Long> {
    // 1. 쿼리 메서드

    // 특정 사용자가 선호하는지 확인
    boolean existsByMemberAndCategory(Member member, FoodCategory category);

    // 2. @Query 어노테이션 (JPQL 작성)

    // 특정 사용자(memberId)가 선호하는 '음식 카테고리(FoodCategory)' 목록만 반환
    @Query("SELECT pf.category FROM PreferFood pf WHERE pf.member.memberId = :memberId")
    List<FoodCategory> findFoodCategoriesByMemberId(@Param("memberId") Long memberId);
}