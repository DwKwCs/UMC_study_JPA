package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.Review;

public interface ReviewQueryService {
    Page<Review> getReviewList(Long restaurantId, Integer page);
    Page<Review> getUserReviewList(Long userId, Integer page);
}
