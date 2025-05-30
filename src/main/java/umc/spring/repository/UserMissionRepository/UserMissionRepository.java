package umc.spring.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.enums.MissionState;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.User;
import umc.spring.domain.mapping.UserMission;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Optional<UserMission> findByUserIdAndMissionId(User user, Mission mission);
    Page<UserMission> findAllByUserIdAndState(User user, MissionState state, Pageable Pageable);
}