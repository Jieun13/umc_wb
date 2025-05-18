package umc.wb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.wb.domain.Member;
import umc.wb.domain.Restaurant;
import umc.wb.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {
            "member", "restaurant"
    })
    Page<Review> findAllByRestaurant(Restaurant restaurant, Pageable pageable);

    @EntityGraph(attributePaths = {
            "member", "restaurant"
    })
    Page<Review> findAllByMember(Member member, Pageable pageable);
}
