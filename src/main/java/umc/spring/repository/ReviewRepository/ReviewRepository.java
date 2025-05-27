package umc.spring.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
