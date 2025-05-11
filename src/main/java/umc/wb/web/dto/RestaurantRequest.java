package umc.wb.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.wb.validation.annotation.ExistCategory;
import umc.wb.validation.annotation.ExistRegion;

public class RestaurantRequest {
    @Getter
    public static class CreateRequest {
        @NotBlank
        private String name;

        @NotNull
        private String address;

        @NotNull
        @ExistCategory
        private Long categoryId;

//        @NotNull
//        @ExistRegion
//        private Long regionId;
    }
}