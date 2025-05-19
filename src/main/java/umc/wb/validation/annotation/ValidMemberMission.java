package umc.wb.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.wb.validation.validator.ValidMemberMissionValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidMemberMissionValidator.class)
@Target( { ElementType.TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMemberMission {
    String message() default "해당 미션이 없습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
