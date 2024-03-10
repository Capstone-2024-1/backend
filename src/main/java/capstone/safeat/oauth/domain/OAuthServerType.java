package capstone.safeat.oauth.domain;

import static java.util.Locale.ENGLISH;

public enum OAuthServerType {

  GOOGLE;

  public static OAuthServerType fromName(final String type) {
    return OAuthServerType.valueOf(type.toUpperCase(ENGLISH));
  }
}
