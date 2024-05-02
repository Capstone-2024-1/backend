package capstone.safeat.group.exception;

import capstone.safeat.base.BaseException;

public class GroupException extends BaseException {

  public GroupException(final GroupExceptionType groupExceptionType) {
    super(groupExceptionType);
  }
}
