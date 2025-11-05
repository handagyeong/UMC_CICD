package spring.umc.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.umc.domain.member.entity.Inquiry;

public interface InquiryRepositoryCustom {

    Page<Inquiry> searchInquiries(
            Long memberId,
            String titleKeyword,
            Pageable pageable
    );
}