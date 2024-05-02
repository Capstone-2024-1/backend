package capstone.safeat.fixture.domain;

import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;

import capstone.safeat.member.domain.Member;
import capstone.safeat.oauth.domain.OAuthMemberId;

public class MemberFixture {

  public static Member 멤버_1() {
    return Member.builder()
        .profileImageUrl("프로필 이미지")
        .oauthMemberId(new OAuthMemberId("member1_id", GOOGLE))
        .build();
  }

  public static Member 멤버_홍혁준_생성() {
    return Member.builder()
        .nickName("홍혁준")
        .profileImageUrl("profile.url")
        .build();
  }
}
