package umc.wb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wb.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
