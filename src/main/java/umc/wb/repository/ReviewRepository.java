package umc.wb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.wb.domain.Restaurant;
import umc.wb.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.member WHERE r.restaurant = :restaurant")
    Page<Review> findAllByRestaurant(Restaurant restaurant, PageRequest pageRequest);
}
