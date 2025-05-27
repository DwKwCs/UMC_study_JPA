package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.mapping.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ExistRestaurant;
import umc.spring.web.dto.ReviewResponse;
import umc.spring.web.dto.ReviewRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
@Validated
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{restaurantId}/reviews")
    public ApiResponse<ReviewResponse.reviewResultDTO> join(
            @ExistRestaurant @PathVariable Long restaurantId,
            @RequestBody @Valid ReviewRequest.reviewDto request) {
        Long userId = 1L;
        Review review = reviewCommandService.joinReview(request, userId, restaurantId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }
}
