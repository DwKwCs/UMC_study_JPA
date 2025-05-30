package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.Restaurants;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByRestaurantId(Restaurants restaurant, Pageable Pageable);
}
