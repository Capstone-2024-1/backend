package capstone.safeat.filter.exception;

import capstone.safeat.base.BaseException;

public class FilterException extends BaseException {

  public FilterException(final FilterExceptionType exceptionType) {
    super(exceptionType);
  }
}
