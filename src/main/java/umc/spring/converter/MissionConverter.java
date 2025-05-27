package umc.spring.converter;

import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.AddChallengeResponse;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;
import java.time.LocalDateTime;


public class MissionConverter {
    public static MissionResponse.missionResultDTO toJoinResultDTO(Mission mission){
        return MissionResponse.missionResultDTO.builder()
                .id(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static AddChallengeResponse.AddChallengeResponseDto toAddChallengeResultDto(UserMission userMission) {
        return AddChallengeResponse.AddChallengeResponseDto.builder()
                .id(userMission.getId())
                .state(userMission.getState())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequest.missionDto request) {
        return Mission.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .phone(request.getPhone())
                .build();
    }
}
