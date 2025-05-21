package umc.spring.repository.RestaurantsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.Restaurants;

public interface RestaurantsRepository extends JpaRepository<Restaurants, Long>, RestaurantsRepositoryCustom {
}