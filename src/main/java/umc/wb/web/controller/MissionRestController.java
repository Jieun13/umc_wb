package umc.wb.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/restaurants/{restaurantId}/missions")
public class MissionRestController {
    private final MissionService missionService;
    private final RestaurantService restaurantService;

    @PostMapping
    public ApiResponse<MissionResponse.CreateMissionResult> createMission(@ExistRestaurant @PathVariable Long restaurantId, @Valid @RequestBody MissionRequest.CreateMissionRequest request){
        Restaurant restaurant = restaurantService.findById(restaurantId);
        Mission mission = missionService.CreateMission(request, restaurant);
        return ApiResponse.onSuccess(MissionMapper.toResponse(mission));
    }
}
