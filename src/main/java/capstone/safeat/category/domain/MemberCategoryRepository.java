package capstone.safeat.category.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {

  List<MemberCategory> findByMemberId(final Long memberId);

  @Query("""
      select mc 
      from MemberCategory  mc
      where mc.memberId in :memberIds
      """)
  List<MemberCategory> findAllByMemberIds(final List<Long> memberIds);

  @Query("""
  delete 
  from MemberCategory mc
  where mc.memberId = :memberId and
  mc.category in :categories
  """)
  @Modifying
  void deleteAllBy(final Long memberId, final List<Category> categories);
}
