package spring.umc.domain.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.member.entity.Inquiry;

public interface InquiryService {

    /**
     * 문의 생성 (비즈니스 로직)
     */
    Inquiry createInquiry(Long memberId, String title, String content, String imageUrl);

    /**
     * 내 문의 목록 조회 (QueryDSL)
     */
    Page<Inquiry> getMyInquiries(Long memberId, String titleKeyword, Pageable pageable);
}