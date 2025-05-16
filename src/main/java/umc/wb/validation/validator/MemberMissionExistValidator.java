package umc.wb.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.service.MemberMissionService;
import umc.wb.validation.annotation.ExistMemberMission;
import umc.wb.web.dto.MemberMissionRequest;

@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<ExistMemberMission, MemberMissionRequest.AddRequest> {
    private final MemberMissionService memberMissionService;


    @Override
    public void initialize(ExistMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequest.AddRequest addRequest, ConstraintValidatorContext context) {
        boolean isExist = memberMissionService.isExist(addRequest.getMemberId(), addRequest.getMissionId());
//        System.out.println("memberMission 존재 여부 : " + isExist);

        if (isExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBERMISSION_ALREADY_EXIST.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
