package umc.spring.web.dto;

import lombok.Getter;

public class ReviewRequest {

    @Getter
    public static class reviewDto{
        String contents;
        Double rate;
    }
}
