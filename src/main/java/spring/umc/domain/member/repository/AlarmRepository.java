package spring.umc.domain.member.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.Alarm;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Dtype;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    // 쿼리 메서드

    // 특정 사용자의 모든 알람 (페이징, 최신순)
    Page<Alarm> findByMemberOrderByCreatedAtDesc(Member member, Pageable pageable);

    // 특정 사용자의 특정 타입 알람
    List<Alarm> findByMemberAndDtype(Member member, Dtype dtype);
}
