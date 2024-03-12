package capstone.safeat.group.exception;

import capstone.safeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum GroupExceptionType implements BaseExceptionType {
  MEMBER_IS_ALREADY_CONTAIN(HttpStatus.BAD_REQUEST, "이미 그룹에 포함된 멤버입니다.");

  private final HttpStatus httpStatus;
  private final String message;

  GroupExceptionType(final HttpStatus httpStatus, final String message) {
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
