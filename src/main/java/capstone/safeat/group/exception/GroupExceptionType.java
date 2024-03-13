package capstone.safeat.group.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import capstone.safeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum GroupExceptionType implements BaseExceptionType {
  MEMBER_IS_ALREADY_CONTAIN(BAD_REQUEST, "이미 그룹에 포함된 멤버입니다."),
  EXECUTORS_IS_NOT_CREATOR(FORBIDDEN, "강퇴를 시킨 사람이 그룹의 생성자가 아닙니다.");

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
