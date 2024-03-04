package capstone.safeat.oauth.domain;

public interface OAuthMemberClient {

  OAuthMemberInfo fetchMember(final String authCode);

  OAuthServerType supportType();
}
