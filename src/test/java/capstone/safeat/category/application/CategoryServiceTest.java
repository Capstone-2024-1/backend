package capstone.safeat.category.application;

import static capstone.safeat.fixture.domain.CategoryDomainFixture.저장된_사과_망고;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.CategoryRepository;
import capstone.safeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryServiceTest extends ServiceTest {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  void 카테고리_조회() {
    final List<Category> expected = 저장된_사과_망고(categoryRepository);

    final List<Category> actual = categoryService.findAllCategory();

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("parent", "children")
        .containsAnyElementsOf(expected);
  }
}