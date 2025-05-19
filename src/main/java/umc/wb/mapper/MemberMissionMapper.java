package umc.wb.mapper;

import org.springframework.data.domain.Page;
import umc.wb.domain.Member;
import umc.wb.domain.MemberMission;
import umc.wb.domain.Mission;
import umc.wb.domain.enums.Status;
import umc.wb.web.dto.MemberMissionRequest;
import umc.wb.web.dto.MemberMissionResponse;
import umc.wb.web.dto.ReviewResponse;

import java.util.List;
import java.util.stream.Collectors;

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

    public static MemberMissionResponse.MemberMissionView toView(MemberMission memberMission){
        return MemberMissionResponse.MemberMissionView.builder()
                .memberName(memberMission.getMember().getName())
                .missionName(memberMission.getMission().getName())
                .RestaurantName(memberMission.getMission().getRestaurant().getName())
                .missionDueDate(memberMission.getMission().getDueDate())
                .missionRewardPoints(memberMission.getMission().getRewardPoints())
                .missionSpentAmount(memberMission.getMission().getSpentAmount())
                .status(memberMission.getStatus())
                .build();
    }

    public static MemberMissionResponse.MemberMissionViewList toViewList  (Page<MemberMission> memberMissions) {
        List<MemberMissionResponse.MemberMissionView> viewLists = memberMissions.stream()
                .map(MemberMissionMapper::toView).collect(Collectors.toList());

        return MemberMissionResponse.MemberMissionViewList.builder()
                .missions(viewLists)
                .isLast(memberMissions.isLast())
                .isFirst(memberMissions.isFirst())
                .totalPage(memberMissions.getTotalPages())
                .listSize(viewLists.size())
                .build();
    }
}