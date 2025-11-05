package spring.umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spring.umc.domain.member.dto.MemberRequestDto;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;
import spring.umc.domain.member.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        return memberService.findMemberById(memberId);
    }

    @GetMapping("/search")
    public Page<Member> searchMembers(
            @RequestParam(required = false) String nameKeyword,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) Status status,
            @PageableDefault(size = 10) Pageable pageable) {
        return memberService.searchMembers(nameKeyword, gender, status, pageable);
    }

    /**
     * 선호 음식 변경 (PreferFood 로직)
     */
    @PutMapping("/{memberId}/prefer-foods")
    public void updatePreferFoods(
            @PathVariable Long memberId,
            @RequestBody MemberRequestDto.UpdatePreferFoodsDto request) {
        memberService.updatePreferFoods(memberId, request.getCategoryIds());
    }
}