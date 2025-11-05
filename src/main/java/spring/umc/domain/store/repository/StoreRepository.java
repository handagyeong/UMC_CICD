package spring.umc.domain.store.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

    // 1. 쿼리 메서드

    /**
     * 지역 이름으로 가게 목록 찾기
     */
    List<Store> findByRegionName(String regionName);

    /**
     * 특정 평점 이상인 가게 찾기 (페이징)
     */
    Page<Store> findByScoreGreaterThanEqual(Float score, Pageable pageable);

    // 2. @Query 어노테이션

    /**
     * 특정 지역에 속하고 이름에 키워드를 포함하는 가게 검색
     */
    @Query("SELECT s FROM Store s WHERE s.region.regionId = :regionId AND s.name LIKE %:nameKeyword%")
    List<Store> findStoresInRegionContainingName(
            @Param("regionId") Long regionId,
            @Param("nameKeyword") String nameKeyword
    );
}