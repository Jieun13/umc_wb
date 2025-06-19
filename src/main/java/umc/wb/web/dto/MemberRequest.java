package umc.wb.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.wb.domain.MemberPreference;
import umc.wb.domain.enums.Role;
import umc.wb.validation.annotation.ExistCategories;

import java.util.ArrayList;
import java.util.List;

public class MemberRequest {
    @Getter
    @Setter
    public static class JoinRequest{
        @NotBlank
        String name;

        @NotBlank
        String phone;

        @Email
        String email;

        @NotBlank
        String password;

        @Size(min = 5, max = 20)
        String address;

        @NotNull
        Integer gender;

        @ExistCategories
        List<Long> preferenceCategory = new ArrayList<>();

        @NotNull
        Role role;
    }

    @Getter
    @Setter
    public static class LoginRequest{
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "패스워드는 필수입니다.")
        private String password;
    }
}