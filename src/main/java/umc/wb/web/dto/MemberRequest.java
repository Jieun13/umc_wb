package umc.wb.web.dto;

import lombok.Getter;
import umc.wb.domain.MemberPreference;

import java.util.List;

public class MemberRequest {
    @Getter
    public static class JoinRequest{
        String name;
        String phone;
//        String email;
        String address;
        Integer gender;
        List<Long> preferenceCategory;
    }
}