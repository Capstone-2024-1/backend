package capstone.safeat.member.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {

  @Query("""
      select mc
      from MemberCategory mc
      join fetch Category c
      on mc.category = c
      where mc.memberId = :memberId
      """)
  List<MemberCategory> findByMemberId(Long memberId);
}
