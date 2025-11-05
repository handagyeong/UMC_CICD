package spring.umc.domain.store.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.store.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    // 쿼리 메서드

    // 이름으로 지역 찾기
    Optional<Region> findByName(String name);
}