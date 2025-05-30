package umc.spring.validation.annotation;

import java.lang.annotation.*;

@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE} )
@Retention(RetentionPolicy.RUNTIME)
public @interface Page1Based {}
