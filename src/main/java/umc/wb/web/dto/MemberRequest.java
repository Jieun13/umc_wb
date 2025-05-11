package umc.wb.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.wb.domain.MemberPreference;
import umc.wb.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequest {
    @Getter
    public static class JoinRequest{
        @NotBlank
        String name;

        @NotNull
        String phone;

//        String email;

        @Size(min = 5, max = 20)
        String address;

        @NotNull
        Integer gender;

        @ExistCategories
        List<Long> preferenceCategory;
    }
}