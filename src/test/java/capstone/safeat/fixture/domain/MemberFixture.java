package capstone.safeat.fixture.domain;

import capstone.safeat.member.domain.Member;

public class MemberFixture {

  public static Member 멤버_홍혁준_생성() {
    return Member.builder()
        .nickName("홍혁준")
        .profileImageUrl("profile.url")
        .build();
  }
}
