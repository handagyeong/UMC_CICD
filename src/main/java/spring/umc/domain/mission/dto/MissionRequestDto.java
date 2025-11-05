package spring.umc.domain.mission.dto;

import lombok.Getter;
import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    public static class CreateMissionDto {
        private String content;
        private Integer point;
        private LocalDate deadline;
        private String imageUrl;
    }
}