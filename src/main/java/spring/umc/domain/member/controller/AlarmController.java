package spring.umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.member.entity.Alarm;
import spring.umc.domain.member.service.AlarmService;

@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    /**
     * 내 알람 목록 조회
     */
    @GetMapping("/my")
    public Page<Alarm> getMyAlarms(
            @RequestParam Long memberId,
            @PageableDefault(size = 10) Pageable pageable) {

        return alarmService.getMyAlarms(memberId, pageable);
    }
}