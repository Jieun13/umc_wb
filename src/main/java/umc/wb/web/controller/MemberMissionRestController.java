package umc.wb.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.wb.apiPayload.ApiResponse;
import umc.wb.domain.Member;
import umc.wb.domain.MemberMission;
import umc.wb.domain.Mission;
import umc.wb.mapper.MemberMissionMapper;
import umc.wb.service.MemberMissionService;
import umc.wb.service.MemberService.MemberCommandServiceImpl;
import umc.wb.service.MissionService;
import umc.wb.web.dto.MemberMissionRequest;
import umc.wb.web.dto.MemberMissionResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
@Validated
public class MemberMissionRestController {

    private final MissionService missionService;
    private final MemberCommandServiceImpl memberCommandServiceImpl;
    private final MemberMissionService memberMissionService;

//    @PostMapping("/{missionId}/challenge")
//    public ApiResponse<MemberMissionResponse.AddResult> challenge(@ExistMission @PathVariable Long missionId, @Valid @RequestBody MemberMissionRequest.AddRequest request) {
//        Mission mission = missionService.findById(missionId);
//        Member member = memberCommandServiceImpl.findById(request.getMemberId());
//        MemberMission memberMission = memberMissionService.addMemberMission(request, mission, member);
//        return ApiResponse.onSuccess(MemberMissionMapper.toResponse(memberMission));
//    }

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponse.AddResult> challenge(@Valid @RequestBody MemberMissionRequest.AddRequest request) {
        Mission mission = missionService.findById(request.getMissionId());
        Member member = memberCommandServiceImpl.findById(request.getMemberId());
        System.out.println("member: " + member + "  mission: " + mission);
        MemberMission memberMission = memberMissionService.addMemberMission(request, mission, member);
        return ApiResponse.onSuccess(MemberMissionMapper.toResponse(memberMission));
    }
}
