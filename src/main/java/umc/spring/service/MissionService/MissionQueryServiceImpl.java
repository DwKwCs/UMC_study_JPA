package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.domain.enums.MissionState;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.Restaurants;
import umc.spring.domain.mapping.Review;
import umc.spring.domain.mapping.User;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.service.RestaurantsService.RestaurantsQueryService;
import umc.spring.service.UserService.UserQueryService;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final RestaurantsQueryService restaurantsQueryService;

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

    @Override
    public Page<Mission> getRestaurantMissionList(Long restaurantId, Integer page) {
        Restaurants restaurant = restaurantsQueryService.findRestaurants(restaurantId).get();

        return missionRepository.findAllByRestaurantId(restaurant, PageRequest.of(page, 10));
    }
}

