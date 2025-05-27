package umc.spring.service.ReviewService;

import umc.spring.domain.mapping.Review;
import umc.spring.web.dto.ReviewRequest;

public interface ReviewCommandService {
    Review joinReview(ReviewRequest.reviewDto request, Long userId, Long restaurantId);
}
