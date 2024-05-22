package capstone.safeat.oauth.external;

import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;

import capstone.safeat.oauth.domain.OAuthMemberClient;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import capstone.safeat.oauth.domain.OAuthServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OAuthMemberClient {

  private final GoogleOauthApiClient googleOauthApiClient;
  private final GoogleOauthConfig googleOAuthConfig;

  @Override
  public OAuthMemberInfo fetchMember(final String authCode) {
    final GoogleOAuthResponse oauthResponse = googleOauthApiClient
        .fetchToken(createRequestParam(authCode));
    final GoogleMemberProfileResponse memberProfileResponse = googleOauthApiClient
        .fetchMemberProfile("Bearer " + oauthResponse.accessToken());
    return new OAuthMemberInfo(memberProfileResponse.id(), memberProfileResponse.picture(), GOOGLE);
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
