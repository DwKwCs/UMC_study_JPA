package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.dto.AddChallengeResponse;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;
import umc.spring.validation.annotation.ExistRestaurant;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Validated
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/restaurants/{restaurantId}/missions")
    public ApiResponse<MissionResponse.missionResultDTO> join(
            @ExistRestaurant @PathVariable Long restaurantId,
            @RequestBody @Valid MissionRequest.missionDto request) {
        Mission mission = missionCommandService.joinMission(request, restaurantId);
        return ApiResponse.onSuccess(MissionConverter.toJoinResultDTO(mission));
    }

    @PostMapping("/userMissions/{userId}/mission")
    public ApiResponse<AddChallengeResponse.AddChallengeResponseDto> addChallenge(
            @PathVariable Long userId) {
        Long missionId = 1L;
        UserMission userMission = missionCommandService.addChallenge(userId, missionId);
        return ApiResponse.onSuccess(MissionConverter.toAddChallengeResultDto(userMission));
    }
}

