package umc.wb.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResult{
        Long id;
        String content;
        Integer rating;
        String memberName;
        String restaurantName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewList{
        List<ReviewPreview> reviews;
        Integer listSize;
        Integer totalPage;
        Integer totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreview{
        String memberName;
        Integer rating;
        LocalDateTime createdAt;
        String content;
        String restaurantName;
    }
}
