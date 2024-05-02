package capstone.safeat.group.application;

import static capstone.safeat.group.exception.GroupExceptionType.GROUP_NOT_FOUND;
import static capstone.safeat.group.exception.GroupExceptionType.MEMBER_IS_NOT_CONTAIN;

import capstone.safeat.group.domain.Group;
import capstone.safeat.group.domain.GroupMember;
import capstone.safeat.group.domain.repository.GroupMemberRepository;
import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.group.exception.GroupException;
import capstone.safeat.group.exception.GroupExceptionType;
import capstone.safeat.member.domain.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class GroupReader {

  private final GroupRepository groupRepository;
  private final GroupMemberRepository groupMemberRepository;

  public List<Group> findGroups(final Member member) {
    final List<GroupMember> groupMember = groupMemberRepository.findByMemberId(member.getId());
    return groupMember.stream()
        .map(GroupMember::getGroup)
        .toList();
  }

  public int countParticipateMember(final Group group) {
    return groupMemberRepository.countByGroup(group);
  }

  public void validateGroupContainMember(final Group group, final Member member) {
    if (!groupMemberRepository.existsByGroupAndMemberId(group, member.getId())) {
      throw new GroupException(MEMBER_IS_NOT_CONTAIN);
    }
  }

  public Group readGroup(final Long groupId) {
    return groupRepository.findById(groupId)
        .orElseThrow(() -> new GroupException(GROUP_NOT_FOUND));
  }
}
