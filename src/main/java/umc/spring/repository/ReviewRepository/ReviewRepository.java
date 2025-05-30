package umc.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.Restaurants;
import umc.spring.domain.mapping.Review;
import umc.spring.domain.mapping.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByRestaurantId(Restaurants restaurant, Pageable Pageable);
    Page<Review> findAllByUserId(User user, Pageable Pageable);
}
