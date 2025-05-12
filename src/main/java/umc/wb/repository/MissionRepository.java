package umc.wb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wb.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
