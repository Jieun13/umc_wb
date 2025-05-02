package umc.wb.repository.RestaurantRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.wb.domain.QRestaurant;
import umc.wb.domain.Restaurant;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom{
    private final QRestaurant restaurant = QRestaurant.restaurant;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Restaurant> dynamicQueryWithBooleanBuilder(String name, Double score){
        BooleanBuilder predicate = new BooleanBuilder();
        if(name!=null){
            predicate.and(restaurant.name.eq(name));
        }
        if(score!=null){
            predicate.and(restaurant.averageRating.goe(4.0));
        }
        return jpaQueryFactory
                .selectFrom(restaurant)
                .where(predicate)
                .fetch();
    }
}
