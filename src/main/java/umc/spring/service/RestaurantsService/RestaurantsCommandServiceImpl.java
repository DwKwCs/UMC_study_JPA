package umc.spring.service.RestaurantsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.RestaurantsRepository.RestaurantsRepository;

@Service
@RequiredArgsConstructor
public class RestaurantsCommandServiceImpl implements RestaurantsCommandService {

    private final RestaurantsRepository restaurantsRepository;

    @Override
    public boolean isRestaurantsExist(Long id) {
        return restaurantsRepository.existsById(id);
    }
}
