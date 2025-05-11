package umc.wb.mapper;

import umc.wb.domain.*;
import umc.wb.web.dto.RestaurantRequest;
import umc.wb.web.dto.RestaurantResponse;

public class RestaurantMapper {
    public static Restaurant toRestaurant(RestaurantRequest.CreateRequest request, Region region, Category category) {

        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();

        restaurant.setCategory(category);
        restaurant.setRegion(region);

        return restaurant;
    }

    public static RestaurantResponse.CreateResult toResponse(Restaurant restaurant) {
        return RestaurantResponse.CreateResult.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .average_rating(restaurant.getAverageRating())
                .categoryName(restaurant.getCategory().getName())
                .regionName(restaurant.getRegion().getName())
                .build();
    }
}