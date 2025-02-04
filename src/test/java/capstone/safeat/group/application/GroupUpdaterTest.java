package capstone.safeat.group.application;

import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import capstone.safeat.group.domain.Group;
import capstone.safeat.group.domain.GroupMember;
import capstone.safeat.group.domain.repository.GroupMemberRepository;
import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GroupUpdaterTest extends ServiceTest {

  @Autowired
  private GroupUpdater groupUpdater;

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private GroupRepository groupRepository;
  @Autowired
  private GroupMemberRepository groupMemberRepository;

  private Member creator;

  @BeforeEach
  void setUp() {
    creator = memberRepository.save(멤버_홍혁준_생성());
  }

  @Test
  void 멤버를_기준으로_Group을_추가한다() {
    final Group newGroup = groupUpdater.saveNewGroupBy(creator, "그룹_1");

    final boolean exists = groupRepository.existsById(newGroup.getId());
    final List<GroupMember> groupMember = groupMemberRepository.findByGroupId(newGroup.getId());
    assertAll(
        () -> assertThat(exists)
            .isTrue(),
        () -> assertThat(groupMember.size())
            .isEqualTo(1)
    );
  }

  @Test
  void 그룹에서_멤버가_삭제된다() {
    final Group newGroup = groupUpdater.saveNewGroupBy(creator, "그룹_1");

    groupUpdater.removeMember(newGroup.getId(), creator);

    final List<GroupMember> groupMember = groupMemberRepository.findByGroupId(newGroup.getId());
    assertThat(groupMember.size())
        .isEqualTo(0);
  }

  @Test
  void 그룹에서_멤버가_추가된다() {
    final Group newGroup = groupUpdater.saveNewGroupBy(creator, "그룹_1");
    final Member newMember = memberRepository.save(멤버_홍혁준_생성());

    groupUpdater.addMember(newGroup, newMember);

    final List<GroupMember> groupMember = groupMemberRepository.findByGroupId(newGroup.getId());
    assertThat(groupMember.size())
        .isEqualTo(2);
  }

  @Test
  void 그룹을_삭제한다() {
    final Group newGroup = groupUpdater.saveNewGroupBy(creator, "그룹_1");

    groupUpdater.removeGroup(newGroup.getId());

    assertThat(groupRepository.existsById(newGroup.getId()))
        .isFalse();
    assertThat(groupMemberRepository.findByGroupId(newGroup.getId()))
        .isEmpty();
  }
}