package umc.spring.converter;

import java.util.List;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.Review;
import umc.spring.web.dto.ReviewRequest;
import umc.spring.web.dto.ReviewResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static ReviewResponse.ReviewResultDTO toJoinResultDTO(Review review){
        return ReviewResponse.ReviewResultDTO.builder()
                .id(review.getUserId().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequest.ReviewDto request) {
        return Review.builder()
                .contents(request.getContents())
                .rate(request.getRate())
                .build();
    }

    public static ReviewResponse.ReviewPreviewDTO reviewPreviewDTO(Review review) {
        return ReviewResponse.ReviewPreviewDTO.builder()
                .id(review.getId())
                .userId(review.getUserId().getId())
                .restaurantId(review.getRestaurantId().getId())
                .contents(review.getContents())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponse.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviewList) {
        List<ReviewResponse.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreviewDTO).collect(Collectors.toList());

        return ReviewResponse.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}
