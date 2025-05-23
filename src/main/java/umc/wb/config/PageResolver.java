package umc.wb.config;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.apiPayload.exception.GeneralException;
import umc.wb.validation.annotation.ValidPage;

@Component
@RequiredArgsConstructor
public class PageResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ValidPage.class)
                && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String pageParam = webRequest.getParameter("page");
        int page;

        try {
            page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        } catch (NumberFormatException e) {
            throw new GeneralException(ErrorStatus.PAGE_BAD_REQUEST);
        }

        if (page < 1) {
            throw new GeneralException(ErrorStatus.PAGE_BAD_REQUEST);
        }

        return page - 1; // 1 → 0 변환
    }
}
