package spring.umc.domain.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.store.entity.Store;

public interface StoreRepositoryCustom {

    /**
     * 동적 쿼리를 이용한 가게 검색하기
     */
    Page<Store> searchStoresByFilter(
            String regionName,
            Float minScore,
            String nameKeyword,
            Pageable pageable
    );
}