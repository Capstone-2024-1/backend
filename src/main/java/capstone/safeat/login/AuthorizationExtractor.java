package capstone.safeat.login;

import static capstone.safeat.login.exception.LoginExceptionType.INVALID_ACCESS_TOKEN_TYPE;
import static capstone.safeat.login.exception.LoginExceptionType.NOT_FOUND_AUTHORIZATION_TOKEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import capstone.safeat.login.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;

public class AuthorizationExtractor {

  private static final String BEARER_TYPE = "Bearer";

  public static String extract(final HttpServletRequest request) {
    final String authorizationHeader = request.getHeader(AUTHORIZATION);

    validateAuthorizationHeader(authorizationHeader);

    return authorizationHeader.substring(BEARER_TYPE.length()).trim();
  }

  private static void validateAuthorizationHeader(final String authorizationHeader) {
    if (authorizationHeader == null || authorizationHeader.isBlank()) {
      throw new LoginException(NOT_FOUND_AUTHORIZATION_TOKEN);
    }
    if (!authorizationHeader.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
      throw new LoginException(INVALID_ACCESS_TOKEN_TYPE);
    }
  }
}
