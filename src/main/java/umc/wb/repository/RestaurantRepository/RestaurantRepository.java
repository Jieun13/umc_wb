package umc.wb.repository.RestaurantRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wb.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> , RestaurantRepositoryCustom {
}
