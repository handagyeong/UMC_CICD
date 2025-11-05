package spring.umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.mission.entity.MissionImage;

public interface MissionImageRepository extends JpaRepository<MissionImage, Long> {

}