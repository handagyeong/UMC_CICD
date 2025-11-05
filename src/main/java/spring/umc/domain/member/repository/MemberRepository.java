package spring.umc.domain.member.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 1. 쿼리 메서드 (메서드 이름으로 쿼리 생성)

    /**
     * 이메일로 사용자 찾기 (Optional<T> 반환)
     */
    Optional<Member> findByEmail(String email);

    /**
     * 특정 상태(Status)의 모든 사용자 찾기
     */
    List<Member> findAllByStatus(Status status);

    /**
     * 이름으로 활성 회원찾기
     * 'status = ACTIVE'
     */
    List<Member> findByNameAndStatus(String name, Status status);

    // 2. @Query 어노테이션 (JPQL 작성)

    /**
     * 이름으로 활성 회원 찾기 (JPQL)
     * :name 파라미터를 @Param("name")으로 바인딩
     */
    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.status = 'ACTIVE'")
    List<Member> findActiveMemberByName(@Param("name") String name);

    /**
     * Native Query
     * nativeQuery = true 옵션 사용
     */
    @Query(value = "SELECT * FROM member m WHERE m.name = :name AND m.status = 'ACTIVE'", nativeQuery = true)
    List<Member> findActiveMemberByNameNative(@Param("name") String name);

    /**
     * 특정 포인트를 초과하는 특정 성별의 사용자 찾기
     */
    @Query("SELECT m FROM Member m WHERE m.gender = :gender AND m.point > :minPoint")
    List<Member> findMembersByGenderWithMinPoint(
            @Param("gender") Gender gender,
            @Param("minPoint") Integer minPoint
    );
}