package umc.wb.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.service.CategoryService;
import umc.wb.validation.annotation.ExistCategory;

@Component
@RequiredArgsConstructor
public class CategoryExistValidator implements ConstraintValidator<ExistCategory, Long> {

    private final CategoryService categoryService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return false;

        boolean isValid = categoryService.isExist(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
