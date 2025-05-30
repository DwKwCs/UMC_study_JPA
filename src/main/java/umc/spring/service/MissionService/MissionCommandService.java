package umc.spring.service.MissionService;


import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.MissionRequest;

public interface MissionCommandService {
    Mission joinMission(MissionRequest.MissionDto request, Long restaurantId);
    boolean isMissionExist(Long id);
}
