package spring.umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.mission.entity.mapping.MemberMission;
import spring.umc.domain.mission.enums.Status;

public interface MemberMissionRepositoryCustom {

    Page<MemberMission> searchMemberMissions(
            Long memberId,
            Long missionId,
            Status status,
            Pageable pageable
    );
}