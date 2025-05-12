package umc.wb.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequest {

    @Getter
    public static class CreateMissionRequest {
        @NotBlank
        private String name;

        @NotNull
        private Integer rewardPoints;

        @NotNull
        private Integer spentAmount;

        @NotNull
        private LocalDateTime dueDate;
    }
}