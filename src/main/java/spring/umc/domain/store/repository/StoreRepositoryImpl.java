package spring.umc.domain.store.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import spring.umc.domain.store.entity.Store;

// Q-Class import
import static spring.umc.domain.store.entity.QStore.store;
import static spring.umc.domain.store.entity.QRegion.region;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Store> searchStoresByFilter(
            String regionName, Float minScore, String nameKeyword, Pageable pageable
    ) {
        BooleanBuilder builder = new BooleanBuilder();

        // 동적 조건 생성
        if (StringUtils.hasText(regionName)) {
            builder.and(region.name.eq(regionName));
        }

        if (minScore != null) {
            builder.and(store.score.goe(minScore));
        }

        if (StringUtils.hasText(nameKeyword)) {
            builder.and(store.name.containsIgnoreCase(nameKeyword));
        }

        JPAQuery<Store> query = queryFactory
                .selectFrom(store)
                .leftJoin(store.region, region);

        // 페이징된 컨텐츠 조회
        List<Store> content = query
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(storeSort(pageable))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(store.count())
                .from(store)
                .leftJoin(store.region, region)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private OrderSpecifier<?> storeSort(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch (order.getProperty()) {
                    case "score":
                        return new OrderSpecifier<>(direction, store.score);
                    case "name":
                        return new OrderSpecifier<>(direction, store.name);
                    case "createdAt":
                        return new OrderSpecifier<>(direction, store.createdAt);
                }
            }
        }

        return new OrderSpecifier<>(Order.DESC, store.createdAt);
    }
}