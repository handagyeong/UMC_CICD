package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {

    //마이페이지 화면 쿼리
    @Query("SELECT new spring.umc.domain.member.dto.MyPageInfoDTO(m.name, m.email, m.phoneNum, m.id, SUM(ph.point)) " +
            "FROM Member m LEFT JOIN m.pointHistoryList ph ON m.id = ph.member.id " + // 5주차 피드백 수정2
            "WHERE m.id = :userId " +
            "GROUP BY m.id, m.name, m.email, m.phoneNum")

     Object[] findMyPageInfoByUserId(@Param("userId") Long userId);
}