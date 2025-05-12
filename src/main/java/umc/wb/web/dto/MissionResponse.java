package umc.wb.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResult{
        Long id;
        String name;
        Integer spentAmount;
        Integer rewardPoints;
        LocalDateTime dueDate;
        String RestaurantName;
    }
}
