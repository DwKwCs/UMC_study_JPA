package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.AddChallengeResponse;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class MissionConverter {
    public static MissionResponse.MissionResultDTO toJoinResultDTO(Mission mission){
        return MissionResponse.MissionResultDTO.builder()
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

    public static Mission toMission(MissionRequest.MissionDto request) {
        return Mission.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .phone(request.getPhone())
                .build();
    }

    public static MissionResponse.MissionPreviewDTO missionPreviewDTO(Mission mission) {
        return MissionResponse.MissionPreviewDTO.builder()
                .id(mission.getId())
                .restaurantId(mission.getRestaurantId().getId())
                .title(mission.getTitle())
                .contents(mission.getContents())
                .phone(mission.getPhone())
                .build();
    }

    public static MissionResponse.MissionPreviewListDTO MissionPreviewListDTO(Page<Mission> missionList) {
        List<MissionResponse.MissionPreviewDTO> missionPreviewDTOList = missionList.stream()
                .map(MissionConverter::missionPreviewDTO).collect(Collectors.toList());

        return MissionResponse.MissionPreviewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }
}
