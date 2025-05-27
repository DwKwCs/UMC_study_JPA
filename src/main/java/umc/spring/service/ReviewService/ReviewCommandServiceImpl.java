package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.mapping.Restaurants;
import umc.spring.domain.mapping.Review;
import umc.spring.domain.mapping.User;
import umc.spring.repository.RestaurantsRepository.RestaurantsRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.ReviewRequest;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantsRepository restaurantsRepository;

    @Override
    public Review joinReview(ReviewRequest.reviewDto request, Long userId, Long restaurantId) {
        Review newReview = ReviewConverter.toReview(request);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Restaurants restaurant = restaurantsRepository.findById(restaurantId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.RESTAURANT_NOT_FOUND));
        newReview.setUserId(user);
        newReview.setRestaurantId(restaurant);

        return reviewRepository.save(newReview);
    }
}
