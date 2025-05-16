package umc.wb.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.service.MemberService.MemberCommandServiceImpl;
import umc.wb.validation.annotation.ExistMember;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistMember, Long> {

    private final MemberCommandServiceImpl memberService;

    @Override
    public void initialize(ExistMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext context) {
        if (aLong == null) {
            return true;
        }

        boolean isValid = memberService.isExist(aLong);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
