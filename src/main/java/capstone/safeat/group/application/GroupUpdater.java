package capstone.safeat.group.application;

import capstone.safeat.group.domain.GroupDomain;
import capstone.safeat.group.domain.GroupEntity;
import capstone.safeat.group.domain.GroupMember;
import capstone.safeat.group.domain.repository.GroupMemberRepository;
import capstone.safeat.group.domain.repository.GroupRepository;
import capstone.safeat.member.domain.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class GroupUpdater {

  private final GroupRepository groupRepository;
  private final GroupMemberRepository groupMemberRepository;

  public GroupDomain saveNewGroupBy(final Member creator, final String groupName) {
    final GroupEntity groupEntity = GroupEntity.create(groupName, creator);
    groupRepository.save(groupEntity);

    final GroupMember groupMember = new GroupMember(groupEntity, creator);
    groupMemberRepository.save(groupMember);
    return new GroupDomain(
        groupEntity.getId(), groupEntity.getName(), groupEntity.getImageUrl(),
        creator, List.of(creator)
    );
  }

  public void removeMember(final Long groupId, final Member member) {
    groupMemberRepository.removeByGroupIdAndMemberId(groupId, member.getId());
  }
}
