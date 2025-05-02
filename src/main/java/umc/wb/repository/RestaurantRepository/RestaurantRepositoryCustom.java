package umc.wb.repository.RestaurantRepository;

import umc.wb.domain.Restaurant;

import java.util.List;

public interface RestaurantRepositoryCustom {
    List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Double score);
}
