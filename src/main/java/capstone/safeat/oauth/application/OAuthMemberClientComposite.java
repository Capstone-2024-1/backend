package capstone.safeat.oauth.application;

import static capstone.safeat.oauth.domain.OAuthExceptionType.OAUTH_CLIENT_NOT_FOUND;
import static java.util.function.Function.identity;

import capstone.safeat.oauth.domain.OAuthException;
import capstone.safeat.oauth.domain.OAuthMemberClient;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import capstone.safeat.oauth.domain.OAuthServerType;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OAuthMemberClientComposite {

  private final Map<OAuthServerType, OAuthMemberClient> oauthMemberClientMap;

  public OAuthMemberClientComposite(final Set<OAuthMemberClient> oAuthMemberClients) {
    this.oauthMemberClientMap = oAuthMemberClients.stream()
        .collect(Collectors.toMap(OAuthMemberClient::supportType, identity()));
  }

  public OAuthMemberInfo fetchMemberInfo(final OAuthServerType serverType, final String authCode) {
    return getClient(serverType).fetchMember(authCode);
  }

  private OAuthMemberClient getClient(final OAuthServerType serverType) {
    return Optional.ofNullable(oauthMemberClientMap.get(serverType))
        .orElseThrow(() -> new OAuthException(OAUTH_CLIENT_NOT_FOUND));
  }
}
