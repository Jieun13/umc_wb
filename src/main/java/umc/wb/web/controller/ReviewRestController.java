package umc.wb.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wb.apiPayload.ApiResponse;
import umc.wb.domain.Member;
import umc.wb.domain.Restaurant;
import umc.wb.domain.Review;
import umc.wb.mapper.ReviewMapper;
import umc.wb.service.MemberService.MemberCommandServiceImpl;
import umc.wb.service.RestaurantService.RestaurantService;
import umc.wb.service.ReviewService;
import umc.wb.validation.annotation.ExistRestaurant;
import umc.wb.web.dto.ReviewRequest;
import umc.wb.web.dto.ReviewResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/{restaurantId}/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;
    private final RestaurantService restaurantService;
    private final MemberCommandServiceImpl memberCommandServiceImpl;

    @PostMapping
    public ApiResponse<ReviewResponse.CreateReviewResult> createReview(@ExistRestaurant @PathVariable Long restaurantId, @Valid @RequestBody ReviewRequest.CreateReviewRequest request) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        Member member = memberCommandServiceImpl.findById(request.getMemberId());
        Review review = reviewService.createReview(request, member, restaurant);
        return ApiResponse.onSuccess(ReviewMapper.toResponse(review));
    }
}