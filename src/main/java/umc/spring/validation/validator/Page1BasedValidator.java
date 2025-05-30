package umc.spring.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.spring.validation.annotation.Page1Based;
import umc.spring.validation.exception.InvalidPageException;

@Component
public class Page1BasedValidator implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Page1Based.class) &&
                parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String pageParam = request.getParameter("page");

        try {
            int page = Integer.parseInt(pageParam);
            if (page < 1) {
                throw new InvalidPageException("page는 1 이상의 정수여야 합니다.");
            }
            return page - 1;
        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidPageException("page 파라미터가 올바르지 않습니다.");
        }
    }
}

