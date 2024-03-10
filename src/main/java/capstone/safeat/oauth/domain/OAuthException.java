package capstone.safeat.oauth.domain;

import capstone.safeat.base.BaseException;

public class OAuthException extends BaseException {

  public OAuthException(final OAuthExceptionType exceptionType) {
    super(exceptionType);
  }
}
