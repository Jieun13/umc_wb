package umc.wb.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.wb.web.dto.MemberResponse;

public interface MemberQueryService {
    MemberResponse.Info getMemberInfo(HttpServletRequest request);
}
