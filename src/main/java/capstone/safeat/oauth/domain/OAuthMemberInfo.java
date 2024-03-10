package capstone.safeat.oauth.domain;

public record OAuthMemberInfo(
    String oauthServerId,
    String profileImageUrl,
    OAuthServerType oauthServerType
) {

  public OAuthMemberId oauthMemberId() {
    return new OAuthMemberId(oauthServerId, oauthServerType);
  }
}
