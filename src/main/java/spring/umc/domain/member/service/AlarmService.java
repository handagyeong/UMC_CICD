package spring.umc.domain.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.member.entity.Alarm;
import spring.umc.domain.member.enums.Dtype;

public interface AlarmService {

    /**
     * 알람 생성 (비즈니스 로직)
     */
    Alarm createAlarm(Long memberId, Dtype dtype, String title, String content);

    /**
     * 내 알람 목록 조회
     */
    Page<Alarm> getMyAlarms(Long memberId, Pageable pageable);
}