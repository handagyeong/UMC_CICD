package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
    //마이페이지 화면 쿼리
    @Query("SELECT m.name, m.mail, m.phoneNum, m.id, SUM(ph.point) " +
            "FROM Member m LEFT JOIN m.pointHistory ph "+
            "WHERE m.id=:userId " + "Group BY m.id")
    Object[] findMyPageInfoByUserId(@Param("userId") String userId);
}
