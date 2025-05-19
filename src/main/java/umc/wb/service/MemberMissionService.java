package umc.wb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.wb.domain.Member;
import umc.wb.domain.MemberMission;
import umc.wb.domain.Mission;
import umc.wb.domain.enums.Status;
import umc.wb.mapper.MemberMissionMapper;
import umc.wb.repository.MemberMissionRepository;
import umc.wb.repository.MemberRepository;
import umc.wb.repository.MissionRepository;
import umc.wb.web.dto.MemberMissionRequest;
import umc.wb.web.dto.MemberMissionResponse;

@Service
@RequiredArgsConstructor
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberMission addMemberMission(MemberMissionRequest.AddRequest request) {
        Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow(()->new IllegalArgumentException("Mission not found"));
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(()-> new IllegalArgumentException("Member not found"));
        MemberMission memberMission = MemberMissionMapper.toMemberMission(request, mission, member);
        return memberMissionRepository.save(memberMission);
    }

    public boolean isExist(Long memberId, Long missionId) {
        return memberMissionRepository.existsMemberMissionByMemberIdAndMissionId(memberId, missionId);
    }

    @Transactional
    public MemberMission update(Long id, MemberMissionRequest.UpdateStatusRequest request) {
        MemberMission memberMission = memberMissionRepository.findById(id).orElseThrow(()->new IllegalArgumentException("MemberMission not found"));
        memberMission.updateStatus(Status.fromCode(request.getStatusNum()));
        return memberMissionRepository.save(memberMission);
    }

    public Page<MemberMission> getAllByMemberId(Long memberId, int page) {
        return memberMissionRepository.findByMemberId(memberId, PageRequest.of(page, 10));
    }

    public boolean getById(Long aLong) {
        return memberMissionRepository.existsById(aLong);
    }
}
