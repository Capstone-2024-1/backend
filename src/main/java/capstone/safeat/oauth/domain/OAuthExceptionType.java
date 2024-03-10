package capstone.safeat.oauth.domain;

import capstone.safeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum OAuthExceptionType implements BaseExceptionType {

  OAUTH_CLIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 OAuth server는 지원하지 않습니다.");

  private final HttpStatus httpStatus;
  private final String message;

  OAuthExceptionType(final HttpStatus httpStatus, final String message) {
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
