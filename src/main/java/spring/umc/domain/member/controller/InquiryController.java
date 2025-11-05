package spring.umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.member.dto.InquiryRequestDto;
import spring.umc.domain.member.entity.Inquiry;
import spring.umc.domain.member.service.InquiryService;

@RestController
@RequestMapping("/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    /**
     * 문의 생성
     */
    @PostMapping
    public Inquiry createInquiry(
            @RequestParam Long memberId,
            @RequestBody InquiryRequestDto.CreateInquiryDto request) {
        return inquiryService.createInquiry(
                memberId,
                request.getTitle(),
                request.getContent(),
                request.getImageUrl()
        );
    }

    /**
     * 내 문의 목록 조회 (QueryDSL)
     */
    @GetMapping("/my")
    public Page<Inquiry> getMyInquiries(
            @RequestParam Long memberId,
            @RequestParam(required = false) String titleKeyword,
            @PageableDefault(size = 10) Pageable pageable) {
        return inquiryService.getMyInquiries(memberId, titleKeyword, pageable);
    }
}