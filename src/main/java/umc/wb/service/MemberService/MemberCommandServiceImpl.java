package umc.wb.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.apiPayload.exception.handler.CategoryHandler;
import umc.wb.domain.Category;
import umc.wb.domain.Member;
import umc.wb.domain.MemberPreference;
import umc.wb.mapper.MemberMapper;
import umc.wb.mapper.MemberPreferenceMapper;
import umc.wb.repository.CategoryRepository;
import umc.wb.repository.MemberRepository;
import umc.wb.web.dto.MemberRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequest.JoinRequest request) {
        Member newMember = MemberMapper.toMember(request);

        List<Category> categoryList = request.getPreferenceCategory().stream()
                .map(category -> {
                    return categoryRepository.findById(category).orElseThrow(()-> new CategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPreference> preferences = MemberPreferenceMapper.toMemberPreferences(categoryList);

        preferences.forEach(memberPreference -> memberPreference.setMember(newMember));
        return memberRepository.save(newMember);
    }

    public boolean isExist(Long memberId) {
        System.out.println("✅ memberService.isExist 호출됨: " + memberId);
        return memberRepository.existsById(memberId);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(()->new IllegalArgumentException("Member not found"));
    }
}
