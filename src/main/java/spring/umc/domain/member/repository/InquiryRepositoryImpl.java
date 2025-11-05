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
import spring.umc.domain.member.entity.Inquiry;

import java.util.List;

// Q-Class import
import static spring.umc.domain.member.entity.QInquiry.inquiry;

@Repository
@RequiredArgsConstructor
public class InquiryRepositoryImpl implements InquiryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Inquiry> searchInquiries(Long memberId, String titleKeyword, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (memberId != null) {
            builder.and(inquiry.member.memberId.eq(memberId));
        }

        if (StringUtils.hasText(titleKeyword)) {
            builder.and(inquiry.title.containsIgnoreCase(titleKeyword));
        }

        // 컨텐츠 조회
        List<Inquiry> content = queryFactory
                .selectFrom(inquiry)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(inquiry.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(inquiry.count())
                .from(inquiry)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}