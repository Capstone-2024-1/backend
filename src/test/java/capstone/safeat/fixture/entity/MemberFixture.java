package capstone.safeat.fixture.entity;

import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;

import capstone.safeat.member.domain.Member;
import capstone.safeat.oauth.domain.OAuthMemberId;

public class MemberFixture {

  public static Member 멤버_홍혁준_생성() {
    final Member 홍혁준 = Member.builder()
        .profileImageUrl("프로필 이미지")
        .oauthMemberId(new OAuthMemberId("member1_id", GOOGLE))
        .build();
    홍혁준.register("홍혁준");
    return 홍혁준;
  }
}
