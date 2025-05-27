package umc.spring.repository.UserMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.User;
import umc.spring.domain.mapping.UserMission;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Optional<UserMission> findByUserIdAndMissionId(User userId, Mission missionId);
}