package umc.spring.repository.RestaurantsRepository;

import umc.spring.domain.mapping.Restaurants;

import java.util.List;


public interface RestaurantsRepositoryCustom {
    List<Restaurants> dynamicQueryWithBooleanBuilder(String name, Float score);
}