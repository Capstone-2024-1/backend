package capstone.safeat.member.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import capstone.safeat.base.BaseEntity;
import capstone.safeat.oauth.domain.OAuthMemberId;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = PROTECTED)
@ToString
public class Member extends BaseEntity {

  @Id
  @Getter
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Embedded
  @NotNull
  @Getter
  private OAuthMemberId oauthMemberId;

  @Getter
  @NotNull
  private boolean isRegistered;

  private String nickName;

  @Getter
  private String profileImageUrl;

  @Builder
  private Member(final OAuthMemberId oauthMemberId, final String profileImageUrl) {
    this.oauthMemberId = oauthMemberId;
    this.profileImageUrl = profileImageUrl;
    this.isRegistered = false;
  }

  @Builder
  public Member(final String nickName, final String profileImageUrl) {
    this.nickName = nickName;
    this.profileImageUrl = profileImageUrl;
  }

  @Builder
  public static Member createOAuthMember(final OAuthMemberInfo oauthMemberInfo) {
    return new Member(oauthMemberInfo.oauthMemberId(), oauthMemberInfo.profileImageUrl());
  }
}
