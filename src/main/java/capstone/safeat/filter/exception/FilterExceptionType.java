package capstone.safeat.filter.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import capstone.safeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum FilterExceptionType implements BaseExceptionType {

  GOOGLE_OCR_NOT_AUTHENTICATE(INTERNAL_SERVER_ERROR, "google ocr이 정상적으로 초기화되지 않았습니다."),
  GOOGLE_OCR_FILTER_FAIL(INTERNAL_SERVER_ERROR, "google ocr이 필터링 되지 않습니다.");

  private final HttpStatus httpStatus;
  private final String message;

  FilterExceptionType(final HttpStatus httpStatus, final String message) {
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
