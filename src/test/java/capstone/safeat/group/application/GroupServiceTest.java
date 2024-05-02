package capstone.safeat.group.application;

import static capstone.safeat.fixture.entity.MemberFixture.멤버_홍혁준_생성;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.group.domain.Group;
import capstone.safeat.group.domain.GroupMember;
import capstone.safeat.group.domain.repository.GroupMemberRepository;
import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.group.dto.GroupPreviewResponse;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import capstone.safeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GroupServiceTest extends ServiceTest {

  @Autowired
  private GroupService groupService;

  @Autowired
  private GroupRepository groupRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private GroupMemberRepository groupMemberRepository;
  @Autowired
  private GroupReader groupReader;
  @Autowired
  private GroupUpdater groupUpdater;

  private Member creator;

  @BeforeEach
  void setUp() {
    creator = memberRepository.save(멤버_홍혁준_생성());
  }

  @Test
  void 그룹을_생성한다() {
    final Long createdGroupId = groupService.createGroup(creator.getId(), "그룹_1");

    final boolean exists = groupRepository.existsById(createdGroupId);
    assertThat(exists)
        .isTrue();
  }

  @Test
  void 그룹에서_멤버가_나간다() {
    final Long createdGroupId = groupService.createGroup(creator.getId(), "그룹_1");

    groupService.leaveGroup(creator.getId(), createdGroupId);

    final List<GroupMember> groupMember = groupMemberRepository.findByGroupId(createdGroupId);
    assertThat(groupMember.size())
        .isEqualTo(0);
  }

  @Test
  void 참여한_그룹_목록을_반환한다() {
    final Long createdGroupId = groupService.createGroup(creator.getId(), "그룹_1");
    final List<GroupPreviewResponse> expected = List.of(
        new GroupPreviewResponse(createdGroupId, "그룹_1", "", 1, creator.getNickName())
    );

    final List<GroupPreviewResponse> groupPreviewResponses
        = groupService.findParticipatedGroups(creator.getId());

    assertThat(groupPreviewResponses)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }

  @Test
  void 그룹의_멤버를_반환한다() {
    final Long createdGroupId = groupService.createGroup(creator.getId(), "그룹_1");

    final List<Member> actual = groupService.findMembersInGroup(creator.getId(), createdGroupId);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrder(creator);
  }

  @Test
  void 그룹에_멤버를_추가한다() {
    final Group group = groupUpdater.saveNewGroupBy(creator, "그룹_1");
    final Member member = memberRepository.save(멤버_홍혁준_생성());

    groupService.participateGroup(group.getId(), member.getId());

    final List<Long> memberIds = groupReader.readParticipateMemberIds(group);

    assertThat(memberIds)
        .contains(member.getId());
  }
}