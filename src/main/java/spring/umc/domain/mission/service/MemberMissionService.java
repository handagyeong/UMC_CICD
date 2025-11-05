package spring.umc.domain.mission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.mission.entity.mapping.MemberMission;
import spring.umc.domain.mission.entity.Mission;

public interface MemberMissionService {

    /**
     * 미션 도전하기 (비즈니스 로직 + 트랜잭션)
     */
    MemberMission challengeMission(Long memberId, Long missionId);

    /**
     * 내 도전 목록 보기
     */
    Page<Mission> getMyChallengingMissions(Long memberId, Pageable pageable);

    /**
     * 미션 완료하기 (기타 메서드)
     * (비즈니스 로직 + 트랜잭션)
     * @param memberId 사용자 ID
     * @param missionId 미션 ID
     * @return 상태가 변경된 MemberMission
     */
    MemberMission completeMission(Long memberId, Long missionId);
}