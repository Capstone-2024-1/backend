package capstone.safeat.group.application;

import capstone.safeat.group.domain.Group;
import capstone.safeat.group.dto.GroupPreviewResponse;
import capstone.safeat.member.application.MemberReader;
import capstone.safeat.member.domain.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final MemberReader memberReader;
  private final GroupUpdater groupUpdater;
  private final GroupReader groupReader;

  @Transactional
  public Long createGroup(final Long memberId, final String groupName) {
    final Member creator = memberReader.readMember(memberId);
    final Group group = groupUpdater.saveNewGroupBy(creator, groupName);
    return group.getId();
  }

  @Transactional
  public void leaveGroup(final Long memberId, final Long groupId) {
    final Member leaver = memberReader.readMember(memberId);
    groupUpdater.removeMember(groupId, leaver);
  }

  @Transactional(readOnly = true)
  public List<GroupPreviewResponse> findParticipatedGroups(final Long memberId) {
    final Member member = memberReader.readMember(memberId);
    final List<Group> groups = groupReader.findGroups(member);

    return groups.stream()
        .map(group -> GroupPreviewResponse.of(
            group,
            groupReader.countParticipateMember(group),
            memberReader.readMember(group.getCreatorId()).getNickName()
        )).toList();
  }

  @Transactional(readOnly = true)
  public List<Member> findMembersInGroup(final Long memberId, final Long groupId) {
    final Member member = memberReader.readMember(memberId);
    final Group group = groupReader.readGroup(groupId);

    groupReader.validateGroupContainMember(group, member);

    final List<Long> memberIds = groupReader.readParticipateMemberIds(group);
    return memberReader.readMembers(memberIds);
  }

  @Transactional
  public void participateGroup(final Long groupId, final Long memberId) {
    final Group group = groupReader.readGroup(groupId);
    final Member member = memberReader.readMember(memberId);

    groupUpdater.addMember(group, member);
  }
}
