package umc.wb.apiPayload.exception.handler;

import umc.wb.apiPayload.code.BaseErrorCode;
import umc.wb.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
