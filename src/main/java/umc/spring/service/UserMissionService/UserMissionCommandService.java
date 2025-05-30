package umc.spring.service.UserMissionService;

import umc.spring.domain.mapping.UserMission;

public interface UserMissionCommandService {
    UserMission addChallenge(Long userId, Long missionId);
    UserMission updateUserMission(Long userId, Long missionId);
}
