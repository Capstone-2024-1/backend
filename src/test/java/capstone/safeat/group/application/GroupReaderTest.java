package capstone.safeat.group.application;

import static capstone.safeat.fixture.entity.GroupFixture.새로운_그룹_생성;
import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;
import static capstone.safeat.group.exception.GroupExceptionType.GROUP_NOT_FOUND;
import static capstone.safeat.group.exception.GroupExceptionType.MEMBER_IS_NOT_CONTAIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import capstone.safeat.group.domain.Group;
import capstone.safeat.group.domain.GroupMember;
import capstone.safeat.group.domain.repository.GroupMemberRepository;
import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.group.exception.GroupException;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.RepositoryTest;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GroupReaderTest extends RepositoryTest {

  @Autowired
  private GroupReader groupReader;

  @Autowired
  private GroupUpdater groupUpdater;
  @Autowired
  private GroupRepository groupRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private GroupMemberRepository groupMemberRepository;

  @Nested
  class 그룹을_읽는다 {

    @Test
    void 성공적으로_조회한다() {
      final Member member = memberRepository.save(멤버_홍혁준_생성());
      final Group savedGroup = groupRepository.save(새로운_그룹_생성(member));

      final Group actual = groupReader.readGroup(savedGroup.getId());

      assertThat(actual)
          .usingRecursiveComparison()
          .isEqualTo(savedGroup);
    }

    @Test
    void 그룹을_찾지못한경우_예외처리한다() {
      assertThatThrownBy(() -> groupReader.readGroup(Long.MAX_VALUE))
          .isInstanceOf(GroupException.class)
          .hasMessageContaining(GROUP_NOT_FOUND.getMessage());
    }
  }

  @Test
  void 멤버가_속한_그룹을_찾는다() {
    final Member member = memberRepository.save(멤버_홍혁준_생성());
    final Group group1 = groupUpdater.saveNewGroupBy(member, "그룹_1");
    final Group group2 = groupUpdater.saveNewGroupBy(member, "그룹_2");
    final Group group3 = groupUpdater.saveNewGroupBy(member, "그룹_3");
    final List<Group> expected = List.of(group1, group2, group3);

    final List<Group> actual = groupReader.findGroups(member);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }

  @Test
  void 그룹에_속한_멤버의_수를_반환한다() {
    final Member member = memberRepository.save(멤버_홍혁준_생성());
    final Member member1 = memberRepository.save(멤버_홍혁준_생성());
    final Member member2 = memberRepository.save(멤버_홍혁준_생성());

    final Group group = groupUpdater.saveNewGroupBy(member, "그룹_1");

    groupMemberRepository.save(new GroupMember(group, member1.getId()));
    groupMemberRepository.save(new GroupMember(group, member2.getId()));

    final int actual = groupReader.countParticipateMember(group);

    assertThat(actual)
        .isEqualTo(3);
  }

  @Nested
  class 사용자가_그룹에_포함되어있는지_검증 {

    @Test
    void 그룹에_포함_안_되어_있으면_예외처리한다() {
      final Member member = memberRepository.save(멤버_홍혁준_생성());
      final Member notContainGroupMember = memberRepository.save(멤버_홍혁준_생성());

      final Group group = groupUpdater.saveNewGroupBy(member, "그룹_1");

      assertThatThrownBy(() -> groupReader.validateGroupContainMember(group, notContainGroupMember))
          .isInstanceOf(GroupException.class)
          .hasMessage(MEMBER_IS_NOT_CONTAIN.getMessage());
    }

    @Test
    void 그룹에_포함되어_있으면_예외처리하지_않는다() {
      final Member member = memberRepository.save(멤버_홍혁준_생성());

      final Group group = groupUpdater.saveNewGroupBy(member, "그룹_1");

      groupReader.validateGroupContainMember(group, member);
    }
  }

  @Test
  void 그룹의_멤버들을_전부_반환한다() {
    final Member member = memberRepository.save(멤버_홍혁준_생성());
    final Member member1 = memberRepository.save(멤버_홍혁준_생성());
    final Member member2 = memberRepository.save(멤버_홍혁준_생성());

    final Group group = groupUpdater.saveNewGroupBy(member, "그룹_1");

    groupMemberRepository.save(new GroupMember(group, member1.getId()));
    groupMemberRepository.save(new GroupMember(group, member2.getId()));

    final List<Long> memberIds = groupReader.readParticipateMemberIds(group);

    assertThat(memberIds)
        .containsExactlyInAnyOrder(member1.getId(), member2.getId(), member.getId());
  }
}