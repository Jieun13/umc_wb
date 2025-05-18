package umc.wb.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionView{
        String name;
        Integer spentAmount;
        Integer rewardPoints;
        LocalDateTime dueDate;
        String RestaurantName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionViewList{
        List<MissionView> missions;
        Integer listSize;
        Integer totalPage;
        Integer totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
