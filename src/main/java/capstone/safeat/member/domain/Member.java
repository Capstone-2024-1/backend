package capstone.safeat.member.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.oauth.domain.OAuthMemberId;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = PROTECTED)
public class Member {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Getter
  private Long id;

  @Embedded
  private OAuthMemberId oauthMemberId;

  private String nickName;

  private String profileImageUrl;

  private Member(
      final Long id, final OAuthMemberId oauthMemberId, final String profileImageUrl
  ) {
    this.oauthMemberId = oauthMemberId;
    this.profileImageUrl = profileImageUrl;
  }

  @Builder
  public Member(final String nickName, final String profileImageUrl) {
    this.nickName = nickName;
    this.profileImageUrl = profileImageUrl;
  }

  @Builder
  public static Member createOAuthMember(final OAuthMemberInfo oauthMemberInfo) {
    return new Member(null, oauthMemberInfo.oauthMemberId(), oauthMemberInfo.profileImageUrl());
  }
}
