package umc.wb.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.apiPayload.exception.handler.CategoryHandler;
import umc.wb.apiPayload.exception.handler.MemberHandler;
import umc.wb.config.security.jwt.JwtTokenProvider;
import umc.wb.domain.Category;
import umc.wb.domain.Member;
import umc.wb.domain.MemberPreference;
import umc.wb.mapper.MemberMapper;
import umc.wb.mapper.MemberPreferenceMapper;
import umc.wb.repository.CategoryRepository;
import umc.wb.repository.MemberRepository;
import umc.wb.web.dto.MemberRequest;
import umc.wb.web.dto.MemberResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public Member joinMember(MemberRequest.JoinRequest request) {
        Member newMember = MemberMapper.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        List<Long> preferenceCategory = request.getPreferenceCategory();

        if (!preferenceCategory.isEmpty()) {
            List<Category> categoryList = preferenceCategory.stream()
                    .map(category -> categoryRepository.findById(category)
                            .orElseThrow(() -> new CategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND)))
                    .collect(Collectors.toList());

            List<MemberPreference> preferences = MemberPreferenceMapper.toMemberPreferences(categoryList);
            preferences.forEach(memberPreference -> memberPreference.setMember(newMember));
        }

        return memberRepository.save(newMember);
    }

    public boolean isExist(Long memberId) {
        System.out.println("✅ memberService.isExist 호출됨: " + memberId);
        return memberRepository.existsById(memberId);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(()->new IllegalArgumentException("Member not found"));
    }

    @Override
    public MemberResponse.LoginResult loginMember(MemberRequest.LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return MemberMapper.toLoginResult(
                member.getId(),
                accessToken
        );
    }
}
