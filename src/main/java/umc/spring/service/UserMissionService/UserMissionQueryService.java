package umc.spring.service.UserMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.UserMission;

public interface UserMissionQueryService {
    Page<UserMission> getUserInprogressMissionList(Long userId, Integer page);
}
