package umc.wb.mapper;

import umc.wb.domain.Member;
import umc.wb.domain.Restaurant;
import umc.wb.domain.Review;
import umc.wb.web.dto.ReviewRequest;
import umc.wb.web.dto.ReviewResponse;

public class ReviewMapper {
    public static Review toReview(ReviewRequest.CreateReviewRequest request, Member member, Restaurant restaurant) {
        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .build();

        review.setMember(member);
        review.setRestaurant(restaurant);

        return review;
    }

    public static ReviewResponse.CreateReviewResult toResponse(Review review) {
        return ReviewResponse.CreateReviewResult.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .memberName(review.getMember().getName())
                .restaurantName(review.getRestaurant().getName())
                .build();
    }
}