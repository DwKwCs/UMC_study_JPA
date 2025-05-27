package umc.spring.web.dto;

import lombok.Getter;

public class MissionRequest {

    @Getter
    public static class missionDto{
        String title;
        String contents;
        String phone;
    }
}
