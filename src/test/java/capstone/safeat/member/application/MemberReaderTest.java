package capstone.safeat.member.application;

import static capstone.safeat.fixture.domain.MemberFixture.멤버_1;
import static capstone.safeat.member.exception.MemberExceptionType.MEMBER_NOT_FOUND;
import static capstone.safeat.oauth.domain.OAuthServerType.GOOGLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.member.exception.MemberException;
import capstone.safeat.oauth.domain.OAuthMemberInfo;
import capstone.safeat.support.RepositoryTest;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberReaderTest extends RepositoryTest {

  @Autowired
  private MemberReader memberReader;

  @Autowired
  private MemberRepository memberRepository;

  @Nested
  class id로_멤버를_조회한다 {

    @Test
    void 멤버를_정상적으로_조회한다() {
      final Member expected = memberRepository.save(멤버_1());

      final Member actual = memberReader.readMember(expected.getId());

      assertThat(actual)
          .usingRecursiveComparison()
          .isEqualTo(expected);
    }

    @Test
    void 멤버가_없는경우_Exception을_반환한다() {
      assertThatThrownBy(() -> memberReader.readMember(Long.MAX_VALUE))
          .isInstanceOf(MemberException.class)
          .hasMessageContaining(MEMBER_NOT_FOUND.getMessage());
    }
  }

  @Nested
  class OAuthMemberId로_멤버를_조회한다 {

    @Test
    void 멤버를_정상적으로_조회한다() {
      final Member expected = memberRepository.save(멤버_1());

      final OAuthMemberInfo oauthMemberInfo = new OAuthMemberInfo(
          expected.getOauthMemberId().getOauthServerId(),
          expected.getProfileImageUrl(),
          expected.getOauthMemberId().getOauthServerType()
      );

      final Optional<Member> actual = memberReader.readBy(oauthMemberInfo);

      assertThat(actual).isPresent();
      assertThat(actual.get())
          .usingRecursiveComparison()
          .isEqualTo(expected);
    }

    @Test
    void 멤버가_없는경우_Exception을_반환한다() {
      final OAuthMemberInfo oauthMemberInfo = new OAuthMemberInfo("1234567890", "image", GOOGLE);

      final Optional<Member> actual = memberReader.readBy(oauthMemberInfo);

      assertThat(actual)
          .isEmpty();
    }
  }
}
