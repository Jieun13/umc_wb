package umc.wb.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.apiPayload.exception.handler.MemberHandler;
import umc.wb.config.security.jwt.JwtTokenProvider;
import umc.wb.domain.Member;
import umc.wb.mapper.MemberMapper;
import umc.wb.repository.MemberRepository;
import umc.wb.web.dto.MemberResponse;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(readOnly = true)
    public MemberResponse.Info getMemberInfo(HttpServletRequest request) {
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberMapper.toMemberInfo(member);
    }
}
