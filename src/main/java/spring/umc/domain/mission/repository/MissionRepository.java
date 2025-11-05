package spring.umc.domain.mission.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.store.entity.Store;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {

    // 1. 쿼리 메서드

    /**
     * 특정 가게(Store)에 할당된 모든 미션 찾기
     */
    List<Mission> findByStore(Store store);

    /**
     * 마감일(deadline)이 아직 지나지 않은 미션 찾기
     */
    List<Mission> findByDeadlineAfter(LocalDate today);
}