package capstone.safeat.fixture.docs;

import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;

import capstone.safeat.member.domain.Member;
import capstone.safeat.oauth.domain.OAuthMemberId;
import java.util.List;

public class MemberFixture {

  public static Member 멤버_홍혁준_생성() {
    final Member 홍혁준 = Member.builder()
        .id(20L)
        .profileImageUrl("프로필 이미지")
        .oauthMemberId(new OAuthMemberId("member1_id", GOOGLE))
        .build();
    홍혁준.updateNickName("홍혁준");
    return 홍혁준;
  }

  public static Member 멤버_전영은_생성() {
    final Member 전영은 = Member.builder()
        .id(21L)
        .profileImageUrl("프로필 이미지")
        .oauthMemberId(new OAuthMemberId("member1_id", GOOGLE))
        .build();
    전영은.updateNickName("전영은");
    return 전영은;
  }

  public static Member 멤버_김동우_생성() {
    final Member 김동우 = Member.builder()
        .id(22L)
        .profileImageUrl("프로필 이미지")
        .oauthMemberId(new OAuthMemberId("member1_id", GOOGLE))
        .build();
    김동우.updateNickName("김동우");
    return 김동우;
  }

  public static List<Member> 멤버_3명() {
    return List.of(멤버_홍혁준_생성(), 멤버_김동우_생성(), 멤버_전영은_생성());
  }
}
