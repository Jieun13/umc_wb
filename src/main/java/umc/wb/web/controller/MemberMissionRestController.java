package umc.wb.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
import umc.wb.validation.annotation.ExistMemberMission;
import umc.wb.validation.annotation.ValidMemberMission;
import umc.wb.web.dto.MemberMissionRequest;
import umc.wb.web.dto.MemberMissionResponse;
import umc.wb.web.dto.MissionResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
@Validated
public class MemberMissionRestController {

    private final MemberMissionService memberMissionService;

    @PostMapping("/challenge")
    @Operation(summary = "미션 도전하기 API", description = "가게의 미션을 도전 중인 미션으로 추가하는 API 입니다.")
    public ApiResponse<MemberMissionResponse.AddResult> challenge(@Valid @RequestBody MemberMissionRequest.AddRequest request) {
        MemberMission memberMission = memberMissionService.addMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionMapper.toResponse(memberMission));
    }

    @PatchMapping("/{memberMissionId}")
    @Operation(summary = "미션 진행 상태 변경하기 API", description = "미션의 진행상태를 변경하는 API 입니다.")
    @Parameters({
            @Parameter(name = "memberMissionId", description = "도전 중인 미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberMissionResponse.MemberMissionView> updateMissionStatus(@ValidMemberMission @PathVariable Long memberMissionId, @Valid @RequestBody MemberMissionRequest.UpdateStatusRequest request){
        MemberMission memberMission = memberMissionService.update(memberMissionId, request);
        return ApiResponse.onSuccess(MemberMissionMapper.toView(memberMission));
    }
    //여기서 membermission이 존재하지 않을 경우 에러 띄워야 하는데 어떡할까.... 일단 상태 변경 자체는 잘 됨!
}
