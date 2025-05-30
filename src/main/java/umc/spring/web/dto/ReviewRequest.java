package umc.spring.web.dto;

import lombok.Getter;

public class ReviewRequest {

    @Getter
    public static class ReviewDto {
        String contents;
        Double rate;
    }
}
