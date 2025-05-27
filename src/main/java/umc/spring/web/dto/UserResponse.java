package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.GenderState;
import umc.spring.domain.enums.UserState;

import java.time.LocalDateTime;

public class UserResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long loginId;
        String password;
        String phone;
        String name;
        String address;
        String nickname;
        GenderState gender;
        String birth;
        UserState status;
        LocalDateTime inactiveDate;
    }
}
