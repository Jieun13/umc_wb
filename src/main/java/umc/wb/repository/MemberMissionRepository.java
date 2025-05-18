package umc.wb.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.wb.domain.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsMemberMissionByMemberIdAndMissionId(Long memberId, Long missionId);

    @EntityGraph(attributePaths = {
            "member", "mission", "mission.restaurant"
    })
    Optional<MemberMission> findById(Long id);
    MemberMission findByMemberIdAndMissionId(Long memberId, Long missionId);
}
