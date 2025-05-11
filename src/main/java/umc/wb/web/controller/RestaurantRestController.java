package umc.wb.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wb.apiPayload.ApiResponse;
import umc.wb.domain.Restaurant;
import umc.wb.mapper.RestaurantMapper;
import umc.wb.service.RestaurantService.RestaurantService;
import umc.wb.web.dto.RestaurantRequest;
import umc.wb.web.dto.RestaurantResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regions")
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    @PostMapping("/{regionId}/restaurants")
    public ApiResponse<RestaurantResponse.CreateResult> addRestaurant(@PathVariable Long regionId, @Valid @RequestBody RestaurantRequest.CreateRequest request){
        Restaurant restaurant = restaurantService.createRestaurant(regionId, request);
        return ApiResponse.onSuccess(RestaurantMapper.toResponse(restaurant));
    }
}
