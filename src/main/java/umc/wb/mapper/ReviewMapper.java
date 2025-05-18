package umc.wb.mapper;

import org.springframework.data.domain.Page;
import umc.wb.domain.Member;
import umc.wb.domain.Restaurant;
import umc.wb.domain.Review;
import umc.wb.web.dto.ReviewRequest;
import umc.wb.web.dto.ReviewResponse;

import java.util.List;
import java.util.stream.Collectors;

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

    public static ReviewResponse.ReviewPreviewList reviewPreviewList(Page<Review> reviews) {
        List<ReviewResponse.ReviewPreview> reviewPreviews = reviews.stream()
                .map(ReviewMapper::reviewPreview).collect(Collectors.toList());

        return ReviewResponse.ReviewPreviewList.builder()
                .isLast(reviews.isLast())
                .isFirst(reviews.isFirst())
                .totalPage(reviews.getTotalPages())
                .listSize(reviewPreviews.size())
                .reviews(reviewPreviews)
                .build();
    }

    public static ReviewResponse.ReviewPreview reviewPreview(Review review) {
        return ReviewResponse.ReviewPreview.builder()
                .content(review.getContent())
                .rating(review.getRating())
                .memberName(review.getMember().getName())
                .createdAt(review.getCreatedAt())
                .restaurantName(review.getRestaurant().getName())
                .build();
    }
}