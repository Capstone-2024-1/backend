package capstone.safeat.category.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CategoryTest {

  @Test
  void 카테고리를_Id로_조회한다() {
    final List<Long> ids = List.of(11L, 15L, 32L);
    final List<Category> expected = List.of(Category.APPLE, Category.MANGO, Category.POTATO);

    final List<Category> actual = Category.readAllById(ids);

    assertThat(actual)
        .containsAnyElementsOf(expected);
  }

  @Test
  void 카테고리의_자식들을_조회한다() {
    final Category 가금류 = Category.POULTRY;
    final List<Category> expected = List.of(Category.CHICKEN, Category.DUCK);

    final List<Category> actual = 가금류.getAllChildren();

    assertThat(actual)
        .containsAnyElementsOf(expected);
  }
}