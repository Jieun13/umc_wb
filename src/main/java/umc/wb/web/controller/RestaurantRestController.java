package umc.wb.web.controller;

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
@RequestMapping("/api/regions")
@Validated
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    @PostMapping("/{regionId}/restaurants")
    public ApiResponse<RestaurantResponse.CreateRestaurantResult> addRestaurant(@ExistRegion @PathVariable Long regionId, @Valid @RequestBody RestaurantRequest.CreateRestaurantRequest request){
        Restaurant restaurant = restaurantService.createRestaurant(regionId, request);
        return ApiResponse.onSuccess(RestaurantMapper.toResponse(restaurant));
    }
}
