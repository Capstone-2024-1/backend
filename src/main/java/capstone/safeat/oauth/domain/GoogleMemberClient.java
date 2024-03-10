package capstone.safeat.oauth.domain;

import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;

import capstone.safeat.oauth.GoogleOAuthConfig;
import capstone.safeat.oauth.external.GoogleApiClient;
import capstone.safeat.oauth.external.GoogleOAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OAuthMemberClient {

  private final GoogleApiClient googleApiClient;
  private final GoogleOAuthConfig googleOAuthConfig;

  @Override
  public OAuthMemberInfo fetchMember(final String authCode) {
    final GoogleOAuthResponse oauthResponse = googleApiClient
        .fetchToken(createRequestParam(authCode));
    return null;
  }

  private MultiValueMap<String, String> createRequestParam(final String authCode) {
    final MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
    param.add("grant_type", "authorization_code");
    param.add("code", authCode);
    param.add("redirect_uri", googleOAuthConfig.redirectUri());
    param.add("client_id", googleOAuthConfig.clientId());
    param.add("client_secret", googleOAuthConfig.clientSecret());
    return param;
  }

  @Override
  public OAuthServerType supportType() {
    return GOOGLE;
  }
}
