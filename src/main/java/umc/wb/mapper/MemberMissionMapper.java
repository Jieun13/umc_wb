package umc.wb.mapper;

import umc.wb.domain.Member;
import umc.wb.domain.MemberMission;
import umc.wb.domain.Mission;
import umc.wb.domain.enums.Status;
import umc.wb.web.dto.MemberMissionRequest;
import umc.wb.web.dto.MemberMissionResponse;

public class MemberMissionMapper {
    public static MemberMission toMemberMission(MemberMissionRequest.AddRequest request, Mission mission, Member member) {
        MemberMission memberMission = MemberMission.builder()
                .status(Status.PENDING)
                .build();

        memberMission.setMission(mission);
        memberMission.setMember(member);

        return memberMission;
    }

    public static MemberMissionResponse.AddResult toResponse(MemberMission memberMission) {
        return MemberMissionResponse.AddResult.builder()
                .id(memberMission.getId())
                .status(memberMission.getStatus())
                .missionId(memberMission.getMission().getId())
                .missionName(memberMission.getMission().getName())
                .missionDueDate(memberMission.getMission().getDueDate())
                .memberId(memberMission.getMember().getId())
                .memberName(memberMission.getMember().getName())
                .build();
    }
}
