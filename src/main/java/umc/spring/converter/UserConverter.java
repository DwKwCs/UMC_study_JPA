package umc.spring.converter;

import umc.spring.domain.mapping.User;
import umc.spring.web.dto.UserRequest;
import umc.spring.web.dto.UserResponse;

public class UserConverter {
    public static User toUser(UserRequest.UserDto request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .birth(request.getBirth())
                .genderState(request.getGender())
                .address(request.getAddress())
                .role(request.getRole())
                .build();
    }

    public static UserResponse.UserResultDTO toUserResultDTO(User user) {
        return UserResponse.UserResultDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static UserResponse.LoginResultDTO toLoginResultDTO(Long userId, String accessToken) {
        return UserResponse.LoginResultDTO.builder()
                .userId(userId)
                .accessToken(accessToken)
                .build();
    }

    public static UserResponse.UserInfoDTO toUserInfoDTO(User user) {
        return UserResponse.UserInfoDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGenderState().toString())
                .build();
    }
}