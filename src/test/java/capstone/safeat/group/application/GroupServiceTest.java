package capstone.safeat.group.application;

import static capstone.safeat.fixture.domain.MemberFixture.멤버_홍혁준_생성;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.group.domain.GroupMember;
import capstone.safeat.group.domain.repository.GroupMemberRepository;
import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.ApplicationTest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GroupServiceTest extends ApplicationTest {

  @Autowired
  private GroupRepository groupRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private GroupMemberRepository groupMemberRepository;

  @Autowired
  private GroupService groupService;

  private Member creator;

  @BeforeEach
  void setUp() {
    creator = memberRepository.save(멤버_홍혁준_생성());
  }

  @Test
  void 그룹을_생성한다() {
    final Long createdGroupId = groupService.createGroup(creator.getId());

    final boolean exists = groupRepository.existsById(createdGroupId);
    assertThat(exists)
        .isTrue();
  }

  @Test
  void 그룹에서_멤버가_나간다() {
    final Long createdGroupId = groupService.createGroup(creator.getId());

    groupService.leaveGroup(creator.getId(), createdGroupId);

    final List<GroupMember> groupMember = groupMemberRepository.findByGroupId(createdGroupId);
    assertThat(groupMember.size())
        .isEqualTo(0);
  }
}