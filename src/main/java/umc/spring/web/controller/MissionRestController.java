package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserMissionConverter;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.UserMissionService.UserMissionCommandService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.MissionResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
@Validated
public class MissionRestController {
    private final UserMissionCommandService userMissionCommandService;

    @PutMapping("/{userId}/{missionId}/stateUpdate/success")
    public ApiResponse<MissionResponse.UpdatedMissionResultDTO> toUpdateUserMission(
            @ExistUser @PathVariable Long userId,
            @ExistMission @PathVariable Long missionId
            ) {
        UserMission userMission = userMissionCommandService.updateUserMission(userId, missionId);
        return ApiResponse.onSuccess(UserMissionConverter.updatedUserMissionResponseDTO(userMission));
    }
}

