package umc.spring.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import umc.spring.domain.mapping.User;
import umc.spring.web.dto.UserResponse;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> findUser(Long id);
    UserResponse.UserInfoDTO getUserInfo(HttpServletRequest request);
}
