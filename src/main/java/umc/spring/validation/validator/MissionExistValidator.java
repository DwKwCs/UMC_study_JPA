package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.mapping.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistUser;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {
    private final MissionCommandService missionCommandService;

    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {
        boolean isValid = missionCommandService.isMissionExist(userId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    ErrorStatus.USER_NOT_FOUND.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
