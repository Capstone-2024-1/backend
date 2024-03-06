package capstone.safeat.oauth.domain;

public record OAuthMemberInfo(
    Long id,
    String profileImageUrl,
    OAuthServerType oAuthServerType
) {

}
