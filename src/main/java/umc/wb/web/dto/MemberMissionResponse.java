package umc.wb.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.wb.domain.enums.Status;

import java.time.LocalDateTime;

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
}
