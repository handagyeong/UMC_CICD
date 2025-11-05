package spring.umc.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;

public interface MemberRepositoryCustom {

    Page<Member> searchMembers(
            String nameKeyword,
            Gender gender,
            Status status,
            Pageable pageable
    );
}