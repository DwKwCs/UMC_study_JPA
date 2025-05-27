package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.domain.enums.MissionState;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.User;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public boolean isMissionChallengeable(Long userId, Long missionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MISSION_NOT_FOUND));
        return userMissionRepository.findByUserIdAndMissionId(user, mission)
                .map(userMission -> userMission.getState() == MissionState.INPROGRESS)
                .orElse(true);
    }
}

