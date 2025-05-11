package umc.wb.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RestaurantResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResult {
        private Long id;
        private String name;
        private String address;
        private Double average_rating;
        private String categoryName;
        private String regionName;
    }
}