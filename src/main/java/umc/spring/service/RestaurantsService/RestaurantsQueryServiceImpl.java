package umc.spring.service.RestaurantsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.Restaurants;
import umc.spring.repository.RestaurantsRepository.RestaurantsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantsQueryServiceImpl implements RestaurantsQueryService {

    private final RestaurantsRepository RestaurantsRepository;

    @Override
    public Optional<Restaurants> findRestaurants(Long id) {
        return RestaurantsRepository.findById(id);
    }

    @Override
    public List<Restaurants> findRestaurantsByNameAndRate(String name, Float rate) {
        List<Restaurants> filteredRestaurants = RestaurantsRepository.dynamicQueryWithBooleanBuilder(name, rate);

        filteredRestaurants.forEach(Restaurants -> System.out.println("Rate: " + rate));

        return filteredRestaurants;
    }
}