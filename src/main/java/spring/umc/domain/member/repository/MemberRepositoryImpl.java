package spring.umc.domain.member.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;

// Q-Class import
import static spring.umc.domain.member.entity.QMember.member;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Member> searchMembers(String nameKeyword, Gender gender, Status status, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(nameKeyword)) {
            builder.and(member.name.containsIgnoreCase(nameKeyword));
        }

        if (gender != null) {
            builder.and(member.gender.eq(gender));
        }

        if (status != null) {
            builder.and(member.status.eq(status));
        }

        // 컨텐츠 조회
        List<Member> content = queryFactory
                .selectFrom(member)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(member.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}