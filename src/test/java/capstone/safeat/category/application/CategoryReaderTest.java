package capstone.safeat.category.application;

import static capstone.safeat.fixture.domain.CategoryDomainFixture.과일;
import static capstone.safeat.fixture.domain.CategoryDomainFixture.망고;
import static capstone.safeat.fixture.domain.CategoryDomainFixture.사과;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.CategoryRepository;
import capstone.safeat.support.RepositoryTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryReaderTest extends RepositoryTest {

  @Autowired
  private CategoryReader categoryReader;

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  void 카테고리_아이디들로_읽는다() {
    final Category fruit = categoryRepository.save(과일());
    final Category apple = categoryRepository.save(사과(fruit));
    final Category mango = categoryRepository.save(망고(fruit));

    final List<Category> actual = categoryReader.readAllById(
        List.of(apple.getId(), mango.getId()));

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("parent", "children")
        .containsExactlyInAnyOrder(apple, mango);
  }
}