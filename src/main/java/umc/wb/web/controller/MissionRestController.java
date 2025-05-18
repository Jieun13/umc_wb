package umc.wb.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.wb.apiPayload.ApiResponse;
import umc.wb.domain.Mission;
import umc.wb.domain.Restaurant;
import umc.wb.mapper.MissionMapper;
import umc.wb.service.MissionService;
import umc.wb.service.RestaurantService.RestaurantService;
import umc.wb.validation.annotation.ExistRestaurant;
import umc.wb.web.dto.MissionRequest;
import umc.wb.web.dto.MissionResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class MissionRestController {
    private final MissionService missionService;
    private final RestaurantService restaurantService;

    @PostMapping("/restaurants/{restaurantId}/missions")
    @Operation(summary = "가게에 미션 추가하기 API", description = "가게에 미션을 추가하는 API 입니다.")
    public ApiResponse<MissionResponse.CreateMissionResult> createMission(@ExistRestaurant @PathVariable Long restaurantId, @Valid @RequestBody MissionRequest.CreateMissionRequest request){
        Restaurant restaurant = restaurantService.findById(restaurantId);
        Mission mission = missionService.CreateMission(request, restaurant);
        return ApiResponse.onSuccess(MissionMapper.toResponse(mission));
    }

    @GetMapping("/restaurants/{restaurantId}/missions")
    @Operation(summary = "가게의 미션 목록 조회하기 API", description = "가게에서 진행 중인 미션을 조회하는 API이며, 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponse.MissionViewList> getMissions(@ExistRestaurant @PathVariable Long restaurantId, @RequestParam(name = "page") Integer page) {
        Page<Mission> missions = missionService.getAllByRestaurant(restaurantId, page-1);
        return ApiResponse.onSuccess(MissionMapper.toViewList(missions));
    }
}
