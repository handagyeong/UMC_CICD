package spring.umc.domain.mission.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import spring.umc.domain.mission.entity.mapping.MemberMission;
import spring.umc.domain.mission.enums.Status;

// Q-Class import
import static spring.umc.domain.mission.entity.mapping.QMemberMission.memberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberMission> searchMemberMissions(Long memberId, Long missionId, Status status, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (memberId != null) {
            builder.and(memberMission.member.memberId.eq(memberId));
        }

        if (missionId != null) {
            builder.and(memberMission.mission.missionId.eq(missionId));
        }

        if (status != null) {
            builder.and(memberMission.status.eq(status));
        }

        // 컨텐츠 조회
        List<MemberMission> content = queryFactory
                .selectFrom(memberMission)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}