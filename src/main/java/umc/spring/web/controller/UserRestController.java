package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.UserMissionConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.mapping.Review;
import umc.spring.domain.mapping.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.UserMissionService.UserMissionCommandService;
import umc.spring.service.UserMissionService.UserMissionQueryService;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.validation.annotation.Page1Based;
import umc.spring.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserRestController {
    private final ReviewQueryService reviewQueryService;
    private final UserMissionQueryService userMissionQueryService;
    private final UserMissionCommandService userMissionCommandService;
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/{userId}/addMission")
    public ApiResponse<AddChallengeResponse.AddChallengeResponseDto> addChallenge(
            @PathVariable Long userId) {
        Long missionId = 1L;
        UserMission userMission = userMissionCommandService.addChallenge(userId, missionId);
        return ApiResponse.onSuccess(MissionConverter.toAddChallengeResultDto(userMission));
    }

    @GetMapping("/{userId}/reviews")
    @Operation(
            summary="유저의 리뷰 목록 조회 API",
            description="유저의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요."
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
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponse.ReviewPreviewListDTO> getUserReviewList(
            @ExistUser @PathVariable(name = "userId") Long userId,
            @Page1Based Integer page) {
        Page<Review> reviewList = reviewQueryService.getUserReviewList(userId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviewList));
    }

    @GetMapping("/{userId}/inprogressMissions")
    @Operation(
            summary="유저의 진행중인 미션 목록 조회 API",
            description="유저의 진행중인 미션의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요."
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
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponse.UserMissionPreviewListDTO> getUserInprogressMissionList(
            @ExistUser @PathVariable(name = "userId") Long userId,
            @Page1Based Integer page) {
        Page<UserMission> userInprogressMissionList = userMissionQueryService.getUserInprogressMissionList(userId, page);
        return ApiResponse.onSuccess(UserMissionConverter.userMissionPreviewListDTO(userInprogressMissionList));
    }

    @PostMapping("/join")
    @Operation(summary = "유저 회원가입 API",description = "유저가 회원가입하는 API입니다.")
    public ApiResponse<UserResponse.UserResultDTO> login(@RequestBody @Valid UserRequest.UserDto request) {
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toUserResultDTO(user));
    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public ApiResponse<UserResponse.LoginResultDTO> login(@RequestBody @Valid UserRequest.LoginRequestDTO request) {
        return ApiResponse.onSuccess(userCommandService.loginUser(request));
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API입니다.",
            security = { @SecurityRequirement(name = "JWT TOKEN") }
    )
    public ApiResponse<UserResponse.UserInfoDTO> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(userQueryService.getUserInfo(request));
    }
}
