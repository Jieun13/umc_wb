package umc.wb.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.service.RegionService;
import umc.wb.validation.annotation.ExistRegion;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionService regionService;

    @Override
    public void initialize(ExistRegion constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext context) {
        boolean isVaild = regionService.isRegionExist(aLong);

        if (!isVaild) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.REGION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isVaild;
    }
}