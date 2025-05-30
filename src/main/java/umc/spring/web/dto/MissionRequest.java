package umc.spring.web.dto;

import lombok.Getter;

public class MissionRequest {

    @Getter
    public static class MissionDto {
        String title;
        String contents;
        String phone;
    }
}
