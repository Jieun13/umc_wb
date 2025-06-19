package umc.wb.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.wb.apiPayload.ApiResponse;
import umc.wb.domain.Member;
import umc.wb.domain.MemberMission;
import umc.wb.domain.Review;
import umc.wb.mapper.MemberMapper;
import umc.wb.mapper.MemberMissionMapper;
import umc.wb.mapper.ReviewMapper;
import umc.wb.service.MemberMissionService;
import umc.wb.service.MemberService.MemberCommandService;
import umc.wb.service.MemberService.MemberQueryService;
import umc.wb.service.ReviewService;
import umc.wb.validation.annotation.ExistMember;
import umc.wb.validation.annotation.ValidPage;
import umc.wb.web.dto.MemberMissionResponse;
import umc.wb.web.dto.MemberRequest;
import umc.wb.web.dto.MemberResponse;
import umc.wb.web.dto.ReviewResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Validated
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final ReviewService reviewService;
    private final MemberMissionService memberMissionService;

    @PostMapping
    @Operation(summary = "사용자 가입 API", description = "사용자를 새로 추가하는 API입니다.")
    public ApiResponse<MemberResponse.JoinResult> join(@RequestBody @Valid MemberRequest.JoinRequest request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberMapper.toJoinResult(member));
    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public ApiResponse<MemberResponse.LoginResult> login(@RequestBody @Valid MemberRequest.LoginRequest request) {
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API입니다.",
            security = { @SecurityRequirement(name = "JWT TOKEN") }
    )
    public ApiResponse<MemberResponse.Info> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회하기 API", description = "유저가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponse.ReviewPreviewList> getReviewsByMember(@ExistMember @PathVariable Long memberId, @ValidPage Integer page) {
        Page<Review> reviews = reviewService.getReviewListByMember(memberId, page);
        return ApiResponse.onSuccess(ReviewMapper.reviewPreviewList(reviews));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 조회하기 API", description = "진행 중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberMissionResponse.MemberMissionViewList> getReviews(@ExistMember @PathVariable Long memberId, @ValidPage Integer page) {
        Page<MemberMission> memberMissionViews = memberMissionService.getAllByMemberId(memberId, page);
        return ApiResponse.onSuccess(MemberMissionMapper.toViewList(memberMissionViews));
    }
}
