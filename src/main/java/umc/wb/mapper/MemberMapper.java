package umc.wb.mapper;

import umc.wb.domain.Member;
import umc.wb.domain.enums.Gender;
import umc.wb.domain.enums.MemberStatus;
import umc.wb.web.dto.MemberRequest;
import umc.wb.web.dto.MemberResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberMapper {

    public static MemberResponse.JoinResult toJoinResult(Member member) {
        return MemberResponse.JoinResult.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequest.JoinRequest request) {
        Gender gender = switch (request.getGender()) {
            case 1 -> Gender.MALE;
            case 2 -> Gender.FEMALE;
            case 3 -> Gender.NONE;
            default -> null;
        };

        return Member.builder()
                .name(request.getName())
                .gender(gender)
                .phone(request.getPhone())
//                .email(request.getEmail())
                .address(request.getAddress())
                .status(MemberStatus.ACTIVE)
                .preferences(new ArrayList<>())
                .build();
    }
}