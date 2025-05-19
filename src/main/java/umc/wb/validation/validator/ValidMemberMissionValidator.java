package umc.wb.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.service.MemberMissionService;
import umc.wb.validation.annotation.ValidMemberMission;

@Component
@RequiredArgsConstructor
public class ValidMemberMissionValidator implements ConstraintValidator<ValidMemberMission, Long> {
    private final MemberMissionService memberMissionService;

    @Override
    public void initialize(ValidMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext context) {
        boolean isExist = memberMissionService.getById(aLong);

        if (!isExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBERMISSION_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
