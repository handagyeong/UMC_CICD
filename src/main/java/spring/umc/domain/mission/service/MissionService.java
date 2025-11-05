package spring.umc.domain.mission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.mission.entity.Mission;
import java.time.LocalDate;

public interface MissionService {

    /**
     * 미션 등록 (비즈니스 로직)
     */
    Mission createMission(Long storeId, String content, Integer point, LocalDate deadline, String imageUrl);

    /**
     * 미션 동적 검색 (QueryDSL)
     */
    Page<Mission> searchMissions(
            Long storeId,
            Integer minPoint,
            LocalDate deadlineBefore,
            Pageable pageable
    );
}
