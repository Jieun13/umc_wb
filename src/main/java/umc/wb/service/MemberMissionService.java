package umc.wb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wb.domain.Member;
import umc.wb.domain.MemberMission;
import umc.wb.domain.Mission;
import umc.wb.mapper.MemberMissionMapper;
import umc.wb.repository.MemberMissionRepository;
import umc.wb.web.dto.MemberMissionRequest;

@Service
@RequiredArgsConstructor
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public MemberMission addMemberMission(MemberMissionRequest.AddRequest request, Mission mission, Member member) {
        MemberMission memberMission = MemberMissionMapper.toMemberMission(request, mission, member);
        return memberMissionRepository.save(memberMission);
    }
}
