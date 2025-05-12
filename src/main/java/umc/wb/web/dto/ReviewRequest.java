package umc.wb.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.wb.validation.annotation.ExistMember;

public class ReviewRequest {

    @Getter
    public static class CreateReviewRequest{
        @NotBlank
        private String content;

        @NotNull
        private Integer rating;

        @NotNull
        @ExistMember
        private Long memberId;
    }
}
