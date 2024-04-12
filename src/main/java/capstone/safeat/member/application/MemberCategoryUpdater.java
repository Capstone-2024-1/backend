package capstone.safeat.member.application;

import capstone.safeat.category.domain.Category;
import capstone.safeat.member.domain.Member;
import capstone.safeat.member.domain.MemberCategory;
import capstone.safeat.member.domain.MemberCategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberCategoryUpdater {

  private final MemberCategoryRepository memberCategoryRepository;

  public void saveMemberCategories(final Member member, final List<Category> leafCategories) {
    final List<MemberCategory> memberCategories = leafCategories.stream()
        .map(category -> new MemberCategory(member.getId(), category))
        .toList();
    memberCategoryRepository.saveAll(memberCategories);
  }
}
