package umc.spring.service.UserMissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.domain.enums.MissionState;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserMissionCommandServiceImpl implements UserMissionCommandService {
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Transactional
    @Override
    public UserMission addChallenge(Long userId, Long missionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MISSION_NOT_FOUND));
        UserMission newUserMission = UserMission.builder()
                .state(MissionState.INPROGRESS)
                .build();
        newUserMission.setUserId(user);
        newUserMission.setMissionId(mission);

        return userMissionRepository.save(newUserMission);
    }

    @Transactional
    @Override
    public UserMission updateUserMission(Long userId, Long missionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MISSION_NOT_FOUND));
        UserMission updatedUserMission = userMissionRepository.findByUserIdAndMissionId(user, mission)
                .orElseThrow(() -> new TempHandler(ErrorStatus.INPROGRESS_USER_MISSION_NOT_FOUND));
        updatedUserMission.setState(MissionState.SUCCESS);
        return userMissionRepository.save(updatedUserMission);
    }
}
