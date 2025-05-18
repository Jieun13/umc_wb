package umc.wb.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.wb.validation.annotation.ExistMember;
import umc.wb.validation.annotation.ExistMemberMission;
import umc.wb.validation.annotation.ExistMission;

public class MemberMissionRequest {
    @Getter
    @ExistMemberMission
    public static class AddRequest{
        @NotNull
        @ExistMember
        Long memberId;

        @NotNull
        @ExistMission
        Long missionId;
    }

    @Getter
    public static class UpdateStatusRequest{
        @NotNull
        Integer statusNum;
        //PENDING(0), COMPLETED(1), IN_PROGRESS(2), EXPIRED(3)
    }
}
