package capstone.safeat.category.dto;

import static capstone.safeat.fixture.docs.CategoryDocsFixture.카테고리들;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Category;
import java.util.List;
import org.junit.jupiter.api.Test;

class CategoryResponseTest {

  @Test
  void 카테고리들을_계층형으로_변환한다() {
    final List<CategoryResponse> expected = List.of(
        new CategoryResponse(1L, "Fruit", "과일", List.of(
            new CategoryResponse(3L, "Apple", "사과", List.of()),
            new CategoryResponse(4L, "Mango", "망고", List.of())
        )),
        new CategoryResponse(2L, "Vegetables", "채소", List.of(
            new CategoryResponse(5L, "Root Vegetables", "뿌리채소", List.of(
                new CategoryResponse(7L, "Potato", "감자", List.of()),
                new CategoryResponse(8L, "Sweet Potato", "고구마", List.of())
            )),
            new CategoryResponse(6L, "Fruiting Vegetables", "과일채소", List.of(
                new CategoryResponse(9L, "Cucumber", "오이", List.of()),
                new CategoryResponse(10L, "Pepper", "고추", List.of())
            ))
        ))
    );
    final List<Category> categories = 카테고리들();

    final List<CategoryResponse> actual = CategoryResponse.convertHierarchy(categories);

    assertThat(expected)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(actual);
  }
}
