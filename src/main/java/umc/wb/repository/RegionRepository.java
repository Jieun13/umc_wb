package umc.wb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.wb.domain.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
