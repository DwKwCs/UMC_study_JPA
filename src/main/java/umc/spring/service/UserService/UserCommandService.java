package umc.spring.service.UserService;

import umc.spring.domain.mapping.User;
import umc.spring.web.dto.UserRequest;
import umc.spring.web.dto.UserResponse;

public interface UserCommandService {
    boolean isUserExist(Long id);
    User joinUser(UserRequest.UserDto request);
    UserResponse.LoginResultDTO loginUser(UserRequest.LoginRequestDTO request);
}
