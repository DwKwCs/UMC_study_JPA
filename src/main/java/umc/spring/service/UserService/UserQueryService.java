package umc.spring.service.UserService;

import umc.spring.domain.mapping.User;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> findUser(Long id);
}
