package capstone.safeat.group.application;

import capstone.safeat.group.domain.Group;
import capstone.safeat.group.domain.GroupMember;
import capstone.safeat.group.domain.repository.GroupMemberRepository;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class GroupReader {

  private final MemberRepository memberRepository;
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
}
