package capstone.safeat.category.application;

import static capstone.safeat.fixture.domain.CategoryDomainFixture.저장된_사과_망고;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.CategoryRepository;
import capstone.safeat.category.domain.MemberCategory;
import capstone.safeat.category.domain.MemberCategoryRepository;
import capstone.safeat.support.RepositoryTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryReaderTest extends RepositoryTest {

  @Autowired
  private CategoryReader categoryReader;

  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private MemberCategoryRepository memberCategoryRepository;

  @Test
  void 카테고리_아이디들로_읽는다() {
    final List<Category> savedCategories = 저장된_사과_망고(categoryRepository);
    final List<Long> categoryIds = savedCategories.stream()
        .map(Category::getId)
        .toList();

    final List<Category> actual = categoryReader.readAllById(categoryIds);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("parent", "children")
        .containsExactlyInAnyOrderElementsOf(savedCategories);
  }

  @Test
  void 멤버_아이디로_멤버에_추가된_카테고리들을_조회한다() {
    final List<Category> expected = 저장된_사과_망고(categoryRepository);
    final Long memberId = 1L;

    final List<MemberCategory> memberCategories = expected.stream()
        .map(category -> new MemberCategory(memberId, category))
        .toList();
    memberCategoryRepository.saveAll(memberCategories);

    final List<Category> actual = categoryReader.readCategoriesByMemberId(memberId);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("parent", "children")
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}