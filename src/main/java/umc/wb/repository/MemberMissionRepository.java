package umc.wb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wb.domain.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
