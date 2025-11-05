package spring.umc.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.service.StoreQueryService;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreQueryService storeQueryService;

    /**
     * 가게 동적 검색 (QueryDSL)
     */
    @GetMapping("/search")
    public Page<Store> searchStores(
            @RequestParam(required = false) String regionName,
            @RequestParam(required = false) Float minScore,
            @RequestParam(required = false) String nameKeyword,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return storeQueryService.searchStores(regionName, minScore, nameKeyword, pageable);
    }
}