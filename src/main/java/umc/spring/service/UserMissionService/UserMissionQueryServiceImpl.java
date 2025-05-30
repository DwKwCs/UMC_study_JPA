package umc.spring.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.enums.MissionState;
import umc.spring.domain.mapping.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.service.UserService.UserQueryService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService {
    private final UserQueryService userQueryService;
    private final UserMissionRepository userMissionRepository;

    @Override
    public Page<UserMission> getUserInprogressMissionList(Long userId, Integer page) {
        User user = userQueryService.findUser(userId).get();

        return userMissionRepository.findAllByUserIdAndState(
                user,
                MissionState.INPROGRESS,
                PageRequest.of(page, 10)
        );
    }
}
