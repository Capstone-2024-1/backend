package capstone.safeat.member.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.oauth.domain.OAuthMemberId;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class MemberEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Getter
  private Long id;

  @Embedded
  @NotNull
  private OAuthMemberId oauthMemberId;

  private String nickName;

  private String profileImageUrl;

  private MemberEntity(final OAuthMemberId oauthMemberId, final String profileImageUrl) {
    this.oauthMemberId = oauthMemberId;
    this.profileImageUrl = profileImageUrl;
  }

  public static MemberEntity createOAuthMember(final OAuthMemberInfo oauthMemberInfo) {
    return new MemberEntity(oauthMemberInfo.oauthMemberId(), oauthMemberInfo.profileImageUrl());
  }

  public Member toDomain() {
    return Member.builder()
        .id(id)
        .profileImageUrl(profileImageUrl)
        .nickName(nickName)
        .build();
  }
}