package umc.spring.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
