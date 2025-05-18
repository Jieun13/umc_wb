package umc.wb.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.wb.domain.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddResult{
        Long id;
        Status status;
        Long memberId;
        String memberName;
        Long missionId;
        String missionName;
        LocalDateTime missionDueDate;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionView{
        String memberName;
        String missionName;
        String RestaurantName;
        Status status;
        Integer missionSpentAmount;
        Integer missionRewardPoints;
        LocalDateTime missionDueDate;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionViewList{
        List<MemberMissionResponse.MemberMissionView> missions;
        Integer listSize;
        Integer totalPage;
        Integer totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
