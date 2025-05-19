package umc.wb.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.wb.apiPayload.ApiResponse;
import umc.wb.domain.Restaurant;
import umc.wb.mapper.RestaurantMapper;
import umc.wb.service.RestaurantService.RestaurantService;
import umc.wb.validation.annotation.ExistRegion;
import umc.wb.web.dto.RestaurantRequest;
import umc.wb.web.dto.RestaurantResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    @PostMapping("/regions/{regionId}/restaurants")
    @Operation(summary = "특정 지역에 가게 추가하기 API", description = "해당 지역에 음식점을 추가하는 API 입니다.")
    public ApiResponse<RestaurantResponse.CreateRestaurantResult> addRestaurant(@ExistRegion @PathVariable Long regionId, @Valid @RequestBody RestaurantRequest.CreateRestaurantRequest request){
        Restaurant restaurant = restaurantService.createRestaurant(regionId, request);
        return ApiResponse.onSuccess(RestaurantMapper.toResponse(restaurant));
    }
}
