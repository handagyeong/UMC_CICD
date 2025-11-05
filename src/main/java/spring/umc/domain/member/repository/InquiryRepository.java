package spring.umc.domain.member.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.Inquiry;
import spring.umc.domain.member.entity.Member;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, InquiryRepositoryCustom {
    // 1. 쿼리 메서드

    // 특정 사용자의 모든 문의 (페이징)
    Page<Inquiry> findByMember(Member member, Pageable pageable);

    // 2. @Query 어노테이션 (JPQL 작성)

    // 제목에 키워드가 포함된 문의 검색
    @Query("SELECT i FROM Inquiry i WHERE i.title LIKE %:keyword%")
    List<Inquiry> findByTitleKeyword(@Param("keyword") String keyword);
}