package capstone.safeat.category.application;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.MemberCategory;
import capstone.safeat.category.domain.MemberCategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
@Transactional(readOnly = true)
public class CategoryReader {

  private final MemberCategoryRepository memberCategoryRepository;

  public List<Category> readCategoriesByMemberId(final Long memberId) {
    return memberCategoryRepository.findByMemberId(memberId)
        .stream()
        .map(MemberCategory::getCategory)
        .toList();
  }

  public List<Category> readAllCategoriesByMemberIds(final List<Long> memberIds) {
    return memberCategoryRepository.findAllByMemberIds(memberIds)
        .stream()
        .map(MemberCategory::getCategory)
        .distinct()
        .toList();
  }
}
