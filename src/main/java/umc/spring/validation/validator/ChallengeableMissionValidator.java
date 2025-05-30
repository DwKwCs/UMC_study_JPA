package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.validation.annotation.ChallengeableMission;

@Component
@RequiredArgsConstructor
public class ChallengeableMissionValidator implements ConstraintValidator<ChallengeableMission, Long> {
    private final MissionQueryService missionQueryService;

    @Override
    public void initialize(ChallengeableMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        Long userId = 1L;

        boolean isValid = missionQueryService.isMissionChallengeable(userId, missionId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    ErrorStatus.ALREADY_CHALLENGED_MISSION.toString()).addConstraintViolation();
        }
        return isValid;
    }
}