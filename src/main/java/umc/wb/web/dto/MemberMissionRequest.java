package umc.wb.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.wb.validation.annotation.ExistMember;

public class MemberMissionRequest {
    @Getter
    public static class AddRequest{
        @NotNull
        @ExistMember
        Long memberId;
    }
}
