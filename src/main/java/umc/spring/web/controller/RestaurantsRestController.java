package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.Review;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.validation.annotation.ExistRestaurant;
import umc.spring.validation.annotation.Page1Based;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;
import umc.spring.web.dto.ReviewResponse;
import umc.spring.web.dto.ReviewRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
@Validated
public class RestaurantsRestController {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;
    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/{restaurantId}/writeReview")
    public ApiResponse<ReviewResponse.ReviewResultDTO> join(
            @ExistRestaurant @PathVariable Long restaurantId,
            @RequestBody @Valid ReviewRequest.ReviewDto request) {
        Long userId = 1L;
        Review review = reviewCommandService.joinReview(request, userId, restaurantId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }

    @PostMapping("/restaurants/{restaurantId}/writeMissions")
    public ApiResponse<MissionResponse.MissionResultDTO> join(
            @ExistRestaurant @PathVariable Long restaurantId,
            @RequestBody @Valid MissionRequest.MissionDto request) {
        Mission mission = missionCommandService.joinMission(request, restaurantId);
        return ApiResponse.onSuccess(MissionConverter.toJoinResultDTO(mission));
    }

    @GetMapping("/{restaurantId}/reviews")
    @Operation(
            summary="특정 가게의 리뷰 목록 조회 API",
            description="특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH003", description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH004", description = "access 토큰 만료",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH006", description = "access 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "PAGING001", description = "Page 값이 1 미만임",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponse.ReviewPreviewListDTO> getReviewList(
            @ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
            @Page1Based Integer page) {
        Page<Review> reviewList = reviewQueryService.getReviewList(restaurantId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviewList));
    }

    @GetMapping("/{restaurantId}/readMissions")
    @Operation(
            summary="특정 가게의 미션 목록 조회 API",
            description="특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH003", description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH004", description = "access 토큰 만료",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH006", description = "access 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "PAGING001", description = "Page 값이 1 미만임",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponse.MissionPreviewListDTO> getRestaurantMissionList(
            @ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
            @Page1Based Integer page) {
        Page<Mission> missionList = missionQueryService.getRestaurantMissionList(restaurantId, page);
        return ApiResponse.onSuccess(MissionConverter.MissionPreviewListDTO(missionList));
    }
}
