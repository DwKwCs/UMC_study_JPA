package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.Mission;

public interface MissionQueryService {
    boolean isMissionChallengeable(Long user, Long mission);
    Page<Mission> getRestaurantMissionList(Long userId, Integer page);
}
