package umc.spring.repository.RestaurantsRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.mapping.QRestaurants;
import umc.spring.domain.mapping.Restaurants;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantsRepositoryImpl implements RestaurantsRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QRestaurants restaurants = QRestaurants.restaurants;

    @Override
    public List<Restaurants> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null) {
            predicate.and(restaurants.name.eq(name));
        }

        if (score != null) {
            predicate.and(restaurants.rate.goe(4.0f));
        }

        return jpaQueryFactory
                .selectFrom(restaurants)
                .where(predicate)
                .fetch();
    }
}