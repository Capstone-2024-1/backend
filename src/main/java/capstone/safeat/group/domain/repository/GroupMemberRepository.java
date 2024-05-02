package capstone.safeat.group.domain.repository;

import capstone.safeat.group.domain.Group;
import capstone.safeat.group.domain.GroupMember;
import java.util.List;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

  List<GroupMember> findByGroupId(final Long groupId);

  void removeByGroupIdAndMemberId(final Long groupId, final Long memberId);

  @Query("""
      select gm
      from GroupMember gm
      join fetch gm.group
      where gm.memberId = :memberId
      """)
  List<GroupMember> findByMemberId(final Long memberId);

  int countByGroup(final Group group);
}
