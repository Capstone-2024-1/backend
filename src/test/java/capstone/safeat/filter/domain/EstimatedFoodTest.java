package capstone.safeat.filter.domain;

import static capstone.safeat.category.domain.Category.MANGO;
import static capstone.safeat.category.domain.Category.OTHER_HERBAGE_CROP;
import static capstone.safeat.category.domain.Category.PEPPER;
import static capstone.safeat.category.domain.Category.RICE;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Category;
import java.util.List;
import org.junit.jupiter.api.Test;

class EstimatedFoodTest {

  final EstimatedFood 김치볶음밥
      = new EstimatedFood("음식", List.of(PEPPER, RICE, OTHER_HERBAGE_CROP), false, true);
  final List<Category> filterCategories = List.of(RICE, MANGO);

  @Test
  void 먹을_수_있는_카테고리들을_추출한다() {
    final List<Category> actual = 김치볶음밥.extractCanEatCategories(filterCategories);
    final List<Category> expected = List.of(PEPPER, OTHER_HERBAGE_CROP);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }

  @Test
  void 먹을_수_없는_카테고리들을_추출한다() {
    final List<Category> actual = 김치볶음밥.extractCannotEatCategories(filterCategories);
    final List<Category> expected = List.of(RICE);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}