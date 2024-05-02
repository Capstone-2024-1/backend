package capstone.safeat.member.exception;

import capstone.safeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MemberExceptionType implements BaseExceptionType {

  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 멤버를 찾을 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String message;

  MemberExceptionType(final HttpStatus httpStatus, final String message) {
    this.httpStatus = httpStatus;
    this.message = message;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
