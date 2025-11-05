package spring.umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.mission.dto.MissionRequestDto;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.mapping.MemberMission;
import spring.umc.domain.mission.service.MemberMissionService;
import spring.umc.domain.mission.service.MissionService;

import java.time.LocalDate;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final MemberMissionService memberMissionService;

    /**
     * 미션 등록 (가게 주인)
     */
    @PostMapping
    public Mission createMission(
            @RequestParam Long storeId,
            @RequestBody MissionRequestDto.CreateMissionDto request) {
        return missionService.createMission(
                storeId,
                request.getContent(),
                request.getPoint(),
                request.getDeadline(),
                request.getImageUrl()
        );
    }

    /**
     * 미션 동적 검색 (QueryDSL)
     */
    @GetMapping("/search")
    public Page<Mission> searchMissions(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer minPoint,
            @RequestParam(required = false) LocalDate deadlineBefore,
            @PageableDefault(size = 10) Pageable pageable) {
        return missionService.searchMissions(storeId, minPoint, deadlineBefore, pageable);
    }

    /**
     * 미션 도전하기 (사용자)
     */
    @PostMapping("/{missionId}/challenge")
    public MemberMission challengeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId) {
        return memberMissionService.challengeMission(memberId, missionId);
    }

    /**
     * 미션 완료하기 (사용자)
     */
    @PatchMapping("/{missionId}/complete")
    public MemberMission completeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId) {
        return memberMissionService.completeMission(memberId, missionId);
    }

    /**
     * 내 도전 목록 보기 (사용자)
     */
    @GetMapping("/my")
    public Page<Mission> getMyMissions(
            @RequestParam Long memberId,
            @PageableDefault(size = 10) Pageable pageable) {
        return memberMissionService.getMyChallengingMissions(memberId, pageable);
    }
}