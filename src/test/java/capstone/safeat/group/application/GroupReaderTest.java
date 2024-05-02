package capstone.safeat.group.application;

import static capstone.safeat.fixture.entity.GroupFixture.새로운_그룹_생성;
import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;
import static capstone.safeat.group.exception.GroupExceptionType.GROUP_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import capstone.safeat.group.domain.Group;
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
}