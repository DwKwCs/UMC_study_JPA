package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.Restaurants;
import umc.spring.domain.mapping.Review;
import umc.spring.domain.mapping.User;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.service.RestaurantsService.RestaurantsQueryService;
import umc.spring.service.UserService.UserQueryService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final RestaurantsQueryService restaurantsQueryService;
    private final UserQueryService userQueryService;
    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getReviewList(Long restaurantId, Integer page) {
        Restaurants restaurant = restaurantsQueryService.findRestaurants(restaurantId).get();

        return reviewRepository.findAllByRestaurantId(restaurant, PageRequest.of(page, 10));
    }

    @Override
    public Page<Review> getUserReviewList(Long userId, Integer page) {
        User user = userQueryService.findUser(userId).get();

        return reviewRepository.findAllByUserId(user, PageRequest.of(page, 10));
    }
}