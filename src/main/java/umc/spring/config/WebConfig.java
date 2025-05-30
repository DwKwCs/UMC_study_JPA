package umc.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import umc.spring.validation.validator.Page1BasedValidator;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final Page1BasedValidator page1BasedValidator;

    public WebConfig(Page1BasedValidator page1BasedValidator) {
        this.page1BasedValidator = page1BasedValidator;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(page1BasedValidator);
    }
}

