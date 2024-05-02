package capstone.safeat.group.application;

import capstone.safeat.group.domain.Group;
import capstone.safeat.group.domain.GroupMember;
import capstone.safeat.group.domain.repository.GroupMemberRepository;
import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class GroupUpdater {

  private final GroupRepository groupRepository;
  private final GroupMemberRepository groupMemberRepository;

  public Group saveNewGroupBy(final Member creator, final String groupName) {
    final Group group = Group.create(groupName, creator);
    groupRepository.save(group);

    final GroupMember groupMember = new GroupMember(group, creator.getId());
    groupMemberRepository.save(groupMember);
    return group;
  }

  public void removeMember(final Long groupId, final Member member) {
    groupMemberRepository.removeByGroupIdAndMemberId(groupId, member.getId());
  }

  public void addMember(final Group group, final Member member) {
    groupMemberRepository.save(new GroupMember(group, member.getId()));
  }

  public void removeGroup(final Long groupId) {
    groupMemberRepository.removeByGroupId(groupId);
    groupRepository.deleteById(groupId);
  }
}
