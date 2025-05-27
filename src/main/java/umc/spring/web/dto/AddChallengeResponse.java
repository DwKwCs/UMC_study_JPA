package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionState;

import java.time.LocalDateTime;

public class AddChallengeResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddChallengeResponseDto{
        Long id;
        MissionState state;
        LocalDateTime createdAt;
    }
}
