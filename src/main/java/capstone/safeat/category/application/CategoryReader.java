package capstone.safeat.category.application;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.CategoryRepository;
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

  private final CategoryRepository categoryRepository;
  private final MemberCategoryRepository memberCategoryRepository;

  public List<Category> readAll() {
    return categoryRepository.findAll();
  }

  public List<Category> readAllById(final List<Long> categoryIds) {
    return categoryRepository.findAllById(categoryIds);
  }

  public List<Category> readCategoriesByMemberId(final Long memberId) {
    return memberCategoryRepository.findByMemberId(memberId)
        .stream()
        .map(MemberCategory::getCategory)
        .toList();
  }
}
