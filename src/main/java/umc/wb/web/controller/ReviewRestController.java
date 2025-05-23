package umc.wb.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
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
import umc.wb.validation.annotation.ValidPage;
import umc.wb.web.dto.ReviewRequest;
import umc.wb.web.dto.ReviewResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class ReviewRestController {

    private final ReviewService reviewService;
    private final RestaurantService restaurantService;
    private final MemberCommandServiceImpl memberCommandServiceImpl;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    @Operation(summary = "리뷰 작성하기 API", description = "식당에 대한 리뷰를 작성하는 API입니다.")
    public ApiResponse<ReviewResponse.CreateReviewResult> createReview(@ExistRestaurant @PathVariable Long restaurantId, @Valid @RequestBody ReviewRequest.CreateReviewRequest request) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        Member member = memberCommandServiceImpl.findById(request.getMemberId());
        Review review = reviewService.createReview(request, member, restaurant);
        return ApiResponse.onSuccess(ReviewMapper.toResponse(review));
    }

    @GetMapping("/restaurants/{restaurantId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회하기 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponse.ReviewPreviewList> getReviews(@ExistRestaurant @PathVariable Long restaurantId, @ValidPage Integer page) {
        Page<Review> reviews = reviewService.getReviewList(restaurantId, page);
        return ApiResponse.onSuccess(ReviewMapper.reviewPreviewList(reviews));
    }
}