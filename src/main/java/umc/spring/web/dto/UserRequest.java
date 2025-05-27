package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.enums.GenderState;
import umc.spring.domain.enums.UserState;
import java.time.LocalDateTime;

public class UserRequest {

    @Getter
    public static class joinDto{
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
