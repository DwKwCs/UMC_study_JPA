package umc.spring.converter;

import umc.spring.domain.mapping.Review;
import umc.spring.web.dto.ReviewRequest;
import umc.spring.web.dto.ReviewResponse;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static ReviewResponse.reviewResultDTO toJoinResultDTO(Review review){
        return ReviewResponse.reviewResultDTO.builder()
                .id(review.getUserId().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequest.reviewDto request) {
        return Review.builder()
                .contents(request.getContents())
                .rate(request.getRate())
                .build();
    }
}
