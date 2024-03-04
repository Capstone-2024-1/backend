package capstone.safeat.oauth.domain;

import static capstone.safeat.oauth.domain.OAuthExceptionType.OAUTH_CLIENT_NOT_FOUND;
import static java.util.function.Function.identity;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OAuthMemberClientComposite {

  private final Map<OAuthServerType, OAuthMemberClient> oAuthMemberClientMap;

  public OAuthMemberClientComposite(final Set<OAuthMemberClient> oAuthMemberClients) {
    this.oAuthMemberClientMap = oAuthMemberClients.stream()
        .collect(Collectors.toMap(OAuthMemberClient::supportType, identity()));
  }

  public OAuthMemberInfo fetchMemberInfo(final OAuthServerType serverType, final String authCode) {
    return getClient(serverType).fetchMember(authCode);
  }

  private OAuthMemberClient getClient(final OAuthServerType serverType) {
    return Optional.ofNullable(oAuthMemberClientMap.get(serverType))
        .orElseThrow(() -> new OAuthException(OAUTH_CLIENT_NOT_FOUND));
  }
}
