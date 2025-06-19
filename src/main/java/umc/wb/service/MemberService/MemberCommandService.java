package umc.wb.service.MemberService;

import umc.wb.domain.Member;
import umc.wb.web.dto.MemberRequest;
import umc.wb.web.dto.MemberResponse;

public interface MemberCommandService {
    public Member joinMember(MemberRequest.JoinRequest request);
    MemberResponse.LoginResult loginMember(MemberRequest.LoginRequest request);
}
