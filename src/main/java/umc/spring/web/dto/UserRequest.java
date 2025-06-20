package umc.spring.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.spring.domain.enums.GenderState;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.UserState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRequest {

    @Setter
    @Getter
    public static class UserDto {
        String loginId;
        @NotBlank
        String password;
        String phone;
        @NotNull
        String name;
        @NotBlank
        @Email
        String email;
        @Size(min = 5, max = 50)
        String address;
        String nickname;
        @NotNull
        GenderState gender = GenderState.MALE;
        @NotNull
        LocalDate birth;
        @NotNull
        Role role = Role.USER;
        UserState status;
        LocalDateTime inactiveDate;
    }

    @Getter
    @Setter
    public static class LoginRequestDTO{
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "패스워드는 필수입니다.")
        private String password;
    }
}
