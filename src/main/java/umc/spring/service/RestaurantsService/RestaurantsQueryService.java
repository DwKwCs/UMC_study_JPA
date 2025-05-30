package umc.spring.service.RestaurantsService;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.Restaurants;
import umc.spring.domain.mapping.Review;

import java.util.List;
import java.util.Optional;

public interface RestaurantsQueryService {
    Optional<Restaurants> findRestaurants(Long id);
    List<Restaurants> findRestaurantsByNameAndRate(String name, Float rate);
}