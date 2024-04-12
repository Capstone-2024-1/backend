package capstone.safeat.oauth.domain;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@ToString
public class OAuthMemberId {

  @NotNull
  @Getter
  private String oauthServerId;

  @Enumerated(STRING)
  @NotNull
  @Getter
  private OAuthServerType oauthServerType;

  public OAuthMemberId(final String oauthServerId, final OAuthServerType oauthServerType) {
    this.oauthServerId = oauthServerId;
    this.oauthServerType = oauthServerType;
  }
}
