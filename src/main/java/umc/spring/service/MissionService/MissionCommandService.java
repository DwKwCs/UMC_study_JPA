package umc.spring.service.MissionService;


import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.MissionRequest;

public interface MissionCommandService {
    Mission joinMission(MissionRequest.missionDto request, Long restaurantId);
    UserMission addChallenge(Long userId, Long missionId);
}
