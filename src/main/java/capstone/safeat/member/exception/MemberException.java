package capstone.safeat.member.exception;

import capstone.safeat.base.BaseException;

public class MemberException extends BaseException {

  public MemberException(final MemberExceptionType exceptionType) {
    super(exceptionType);
  }
}
