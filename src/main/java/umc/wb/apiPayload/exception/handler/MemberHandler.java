package umc.wb.apiPayload.exception.handler;

import umc.wb.apiPayload.code.BaseErrorCode;
import umc.wb.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

  public MemberHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}