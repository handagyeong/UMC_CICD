package spring.umc.domain.mission.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import spring.umc.domain.mission.entity.Mission;

import java.time.LocalDate;
import java.util.List;

// Q-Class import
import static spring.umc.domain.mission.entity.QMission.mission;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> searchMissions(Long storeId, Integer minPoint, LocalDate deadlineBefore, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (storeId != null) {
            builder.and(mission.store.storeId.eq(storeId));
        }

        if (minPoint != null) {
            builder.and(mission.point.goe(minPoint));
        }

        if (deadlineBefore != null) {
            builder.and(mission.deadline.before(deadlineBefore));
        }

        // 컨텐츠 조회
        List<Mission> content = queryFactory
                .selectFrom(mission)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(mission.deadline.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(mission.count())
                .from(mission)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}