package umc.wb.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.wb.validation.validator.PageValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {
    String message() default "유효하지 않은 페이지 값입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
