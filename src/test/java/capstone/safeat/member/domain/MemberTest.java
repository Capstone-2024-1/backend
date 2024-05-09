package capstone.safeat.member.domain;

import static capstone.safeat.member.exception.MemberExceptionType.ALREADY_REGISTERED;
import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import capstone.safeat.member.exception.MemberException;
import capstone.safeat.oauth.domain.OAuthMemberId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MemberTest {

  @Nested
  class 회원가입한다 {

    @Test
    void 정상적으로_회원가입_한다() {
      final Member member = Member.builder()
          .profileImageUrl("프로필 이미지")
          .oauthMemberId(new OAuthMemberId("member1_id", GOOGLE))
          .build();
      final String nickname = "홍혁준";

      member.register(nickname);

      assertThat(member.getNickName())
          .isEqualTo(nickname);
    }

    @Test
    void 이미_회원가입된_경우_예외처리한다() {
      final Member member = Member.builder()
          .profileImageUrl("프로필 이미지")
          .oauthMemberId(new OAuthMemberId("member1_id", GOOGLE))
          .build();
      final String nickname = "홍혁준";

      member.register("already Register");

      assertThatThrownBy(() -> member.register(nickname))
          .isInstanceOf(MemberException.class)
          .hasMessage(ALREADY_REGISTERED.getMessage());
    }
  }
}