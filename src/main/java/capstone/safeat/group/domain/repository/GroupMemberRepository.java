package capstone.safeat.group.domain.repository;

import capstone.safeat.group.domain.GroupMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

  List<GroupMember> findByGroupId(final Long groupId);

  void removeByGroupIdAndMemberId(final Long groupId, final Long memberId);
}
