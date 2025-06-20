package umc.spring.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.converter.UserConverter;
import umc.spring.domain.mapping.User;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.UserRequest;
import umc.spring.web.dto.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import umc.spring.config.security.jwt.JwtTokenProvider;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean isUserExist(Long id) {
        return userRepository.existsById(id);
    }

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User joinUser(UserRequest.UserDto request) {

        User newUser = UserConverter.toUser(request);

        newUser.encodePassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public UserResponse.LoginResultDTO loginUser(UserRequest.LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null,
                Collections.singleton(() -> user.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return UserConverter.toLoginResultDTO(
                user.getId(),
                accessToken
        );
    }
}
