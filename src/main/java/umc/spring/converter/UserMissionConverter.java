package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.MissionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserMissionConverter {
    public static MissionResponse.UserMissionPreviewDTO userMissionPreviewDTO(UserMission userMission) {
        return MissionResponse.UserMissionPreviewDTO.builder()
                .id(userMission.getId())
                .userId(userMission.getUserId().getId())
                .missionId(userMission.getMissionId().getId())
                .state(userMission.getState())
                .build();
    }

    public static MissionResponse.UserMissionPreviewListDTO userMissionPreviewListDTO(Page<UserMission> userMissionList) {
        List<MissionResponse.UserMissionPreviewDTO> userMissionPreviewDTOList = userMissionList.stream()
                .map(UserMissionConverter::userMissionPreviewDTO).collect(Collectors.toList());

        return MissionResponse.UserMissionPreviewListDTO.builder()
                .isLast(userMissionList.isLast())
                .isFirst(userMissionList.isFirst())
                .totalPage(userMissionList.getTotalPages())
                .totalElements(userMissionList.getTotalElements())
                .listSize(userMissionPreviewDTOList.size())
                .userMissionList(userMissionPreviewDTOList)
                .build();
    }

    public static MissionResponse.UpdatedMissionResultDTO updatedUserMissionResponseDTO(UserMission userMission) {
        return MissionResponse.UpdatedMissionResultDTO.builder()
                .id(userMission.getId())
                .userId(userMission.getUserId().getId())
                .missionId(userMission.getMissionId().getId())
                .state(userMission.getState())
                .build();
    }
}
