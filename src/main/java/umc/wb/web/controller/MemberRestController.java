package umc.wb.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.wb.apiPayload.ApiResponse;
import umc.wb.domain.Member;
import umc.wb.mapper.MemberMapper;
import umc.wb.service.MemberService.MemberCommandService;
import umc.wb.web.dto.MemberRequest;
import umc.wb.web.dto.MemberResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponse.JoinResult> join(@RequestBody @Valid MemberRequest.JoinRequest request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberMapper.toJoinResult(member));
    }
}
