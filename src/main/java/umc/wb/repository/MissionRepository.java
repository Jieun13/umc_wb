package umc.wb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.wb.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @EntityGraph(attributePaths = {
            "restaurant"
    })
    Page<Mission> findAllByRestaurantId(Long restaurantId, Pageable pageable);
}
