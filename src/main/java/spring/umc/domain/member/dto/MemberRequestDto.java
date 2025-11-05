package spring.umc.domain.member.dto;

import lombok.Getter;
import java.util.List;

public class MemberRequestDto {

    @Getter
    public static class UpdatePreferFoodsDto {
        private List<Long> categoryIds;
    }
}