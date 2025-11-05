package spring.umc.domain.member.dto;

import lombok.Getter;

public class InquiryRequestDto {

    @Getter
    public static class CreateInquiryDto {
        private String title;
        private String content;
        private String imageUrl;
    }
}