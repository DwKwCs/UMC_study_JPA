package umc.spring.repository.RestaurantsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.Restaurants;
import umc.spring.domain.mapping.Review;

public interface RestaurantsRepository extends JpaRepository<Restaurants, Long>, RestaurantsRepositoryCustom {
}