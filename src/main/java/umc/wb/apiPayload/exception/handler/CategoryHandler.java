package umc.wb.apiPayload.exception.handler;

import umc.wb.apiPayload.code.status.ErrorStatus;
import umc.wb.apiPayload.exception.GeneralException;

public class CategoryHandler extends GeneralException {
    public CategoryHandler(ErrorStatus message) {
        super(message);
    }
}
