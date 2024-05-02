package capstone.safeat.group.application;

import static capstone.safeat.group.exception.GroupExceptionType.GROUP_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import capstone.safeat.fixture.entity.GroupFixture;
import capstone.safeat.group.domain.Group;
import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.group.exception.GroupException;
import capstone.safeat.group.exception.GroupExceptionType;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.RepositoryTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GroupReaderTest extends RepositoryTest {

  @Autowired
  private GroupReader groupReader;

  @Autowired
  private GroupRepository groupRepository;
  @Autowired
  private MemberRepository memberRepository;

  @Nested
  class 그룹을_읽는다 {

    @Test
    void 성공적으로_조회한다() {
      final Group unsavedGroup = GroupFixture.새로운_그룹_생성(memberRepository);
      final Group savedGroup = groupRepository.save(unsavedGroup);

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
}