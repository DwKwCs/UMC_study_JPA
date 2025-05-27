package umc.spring.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.User;

public interface UserRepository extends JpaRepository<User, Long>  {
}
