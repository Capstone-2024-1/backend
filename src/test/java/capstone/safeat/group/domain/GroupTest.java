package capstone.safeat.group.domain;

import static capstone.safeat.fixture.domain.GroupFixture.새로운_그룹_생성;
import static capstone.safeat.group.exception.GroupExceptionType.MEMBER_IS_ALREADY_CONTAIN;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.group.exception.GroupException;
import capstone.safeat.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class GroupTest {

  @Nested
  class 멤버를_그룹에_추가한다 {

    @Test
    void 정상적으로_그룹에_추가한다() {
      final Group group = 새로운_그룹_생성();
      final Member newMember = new Member("홍실", "profile");

      group.addMember(newMember);

      assertThat(group.getMembers())
          .contains(newMember);
    }

    @Test
    void 이미_그룹에_추가되어있는경우_예외처리한다() {
      final Group group = 새로운_그룹_생성();
      final Member newMember = new Member("홍실", "profile");
      group.addMember(newMember);

      Assertions.assertThatThrownBy(() -> group.addMember(newMember))
          .isInstanceOf(GroupException.class)
          .hasMessage(MEMBER_IS_ALREADY_CONTAIN.getMessage());
    }
  }
}