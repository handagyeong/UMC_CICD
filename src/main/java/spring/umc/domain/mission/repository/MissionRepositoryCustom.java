package spring.umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.mission.entity.Mission;
import java.time.LocalDate;

public interface MissionRepositoryCustom {

    Page<Mission> searchMissions(
            Long storeId,
            Integer minPoint,
            LocalDate deadlineBefore,
            Pageable pageable
    );
}