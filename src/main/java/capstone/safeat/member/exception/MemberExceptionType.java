package capstone.safeat.member.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import capstone.safeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MemberExceptionType implements BaseExceptionType {

  MEMBER_NOT_FOUND(NOT_FOUND, "해당하는 멤버를 찾을 수 없습니다."),
  ALREADY_REGISTERED(BAD_REQUEST, "이미 회원가입 완료한 회원입니다.");

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
