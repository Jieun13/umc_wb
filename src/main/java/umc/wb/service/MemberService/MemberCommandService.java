package umc.wb.service.MemberService;

import umc.wb.domain.Member;
import umc.wb.web.dto.MemberRequest;

public interface MemberCommandService {
    public Member joinMember(MemberRequest.JoinRequest request);
}
