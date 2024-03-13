package capstone.safeat.group.domain;

import static capstone.safeat.fixture.domain.GroupFixture.새로운_그룹_생성;
import static capstone.safeat.group.exception.GroupExceptionType.EXECUTORS_IS_NOT_CREATOR;
import static capstone.safeat.group.exception.GroupExceptionType.MEMBER_IS_ALREADY_CONTAIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import capstone.safeat.group.exception.GroupException;
import capstone.safeat.member.domain.Member;
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

      assertThatThrownBy(() -> group.addMember(newMember))
          .isInstanceOf(GroupException.class)
          .hasMessage(MEMBER_IS_ALREADY_CONTAIN.getMessage());
    }
  }

  @Test
  void 그룹에서_정상적으로_탈퇴한다() {
    final Group group = 새로운_그룹_생성();
    final Member member = new Member("홍실", "profile");
    group.addMember(member);

    group.drop(member);

    assertThat(group.getMembers())
        .doesNotContain(member);
  }

  @Nested
  class 그룹에서_멤버를_강퇴시킨다 {

    @Test
    void 정상적으로_강퇴시킨다() {
      final Member creator = new Member("그룹장", "groupLeader");
      final Group group = new Group(creator);
      final Member member = new Member("홍실", "profile");
      group.addMember(member);

      group.expel(creator, member);

      assertThat(group.getMembers())
          .doesNotContain(member);
    }

    @Test
    void 강퇴를_시키려는_사람이_그룹_생성자가_아니면_예외처리한다() {
      final Member creator = new Member("그룹장", "groupLeader");
      final Group group = new Group(creator);
      final Member member = new Member("홍실", "profile");
      group.addMember(member);

      assertThatThrownBy(() -> group.expel(member, member))
          .isInstanceOf(GroupException.class)
          .hasMessage(EXECUTORS_IS_NOT_CREATOR.getMessage());
    }
  }
}