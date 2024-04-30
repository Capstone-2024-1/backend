package capstone.safeat.category.dto;

import static capstone.safeat.category.domain.CategoryRefactor.APPLE;
import static capstone.safeat.category.domain.CategoryRefactor.CUCUMBER;
import static capstone.safeat.category.domain.CategoryRefactor.FRUITING_VEGETABLES;
import static capstone.safeat.category.domain.CategoryRefactor.FRUITS;
import static capstone.safeat.category.domain.CategoryRefactor.MANGO;
import static capstone.safeat.category.domain.CategoryRefactor.POTATO;
import static capstone.safeat.category.domain.CategoryRefactor.ROOT_VEGETABLES;
import static capstone.safeat.category.domain.CategoryRefactor.SWEET_POTATO;
import static capstone.safeat.category.domain.CategoryRefactor.VEGETABLES;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.CategoryRefactor;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CategoryResponseTest {

  @Test
  void 카테고리들을_계층형으로_변환한다() {
    final List<CategoryResponse> expected = List.of(
        new CategoryResponse(FRUITS.getId(), FRUITS.getEnglishName(), FRUITS.getKoreanName(),
            Set.of(APPLE.getId(), MANGO.getId()),
            List.of(
                new CategoryResponse(APPLE.getId(), APPLE.getEnglishName(), APPLE.getKoreanName(),
                    Set.of(), List.of()),
                new CategoryResponse(MANGO.getId(), MANGO.getEnglishName(), MANGO.getKoreanName(),
                    Set.of(), List.of())
            )),
        new CategoryResponse(
            VEGETABLES.getId(), VEGETABLES.getEnglishName(), VEGETABLES.getKoreanName(),
            Set.of(ROOT_VEGETABLES.getId(), POTATO.getId(), SWEET_POTATO.getId(),
                FRUITING_VEGETABLES.getId(), CUCUMBER.getId()),
            List.of(
                new CategoryResponse(
                    ROOT_VEGETABLES.getId(), ROOT_VEGETABLES.getEnglishName(),
                    ROOT_VEGETABLES.getKoreanName(), Set.of(POTATO.getId(), SWEET_POTATO.getId()),
                    List.of(
                        new CategoryResponse(POTATO.getId(), POTATO.getEnglishName(),
                            POTATO.getKoreanName(), Set.of(), List.of()),
                        new CategoryResponse(SWEET_POTATO.getId(), SWEET_POTATO.getEnglishName(),
                            SWEET_POTATO.getKoreanName(), Set.of(), List.of())
                    )),
                new CategoryResponse(FRUITING_VEGETABLES.getId(),
                    FRUITING_VEGETABLES.getEnglishName(), FRUITING_VEGETABLES.getKoreanName(),
                    Set.of(CUCUMBER.getId()),
                    List.of(
                        new CategoryResponse(CUCUMBER.getId(), CUCUMBER.getEnglishName(),
                            CUCUMBER.getKoreanName(), Set.of(), List.of())
                    ))
            ))
    );

    final List<CategoryRefactor> categories = List.of(
        FRUITS, CategoryRefactor.APPLE, CategoryRefactor.MANGO,
        CategoryRefactor.VEGETABLES, CategoryRefactor.ROOT_VEGETABLES,
        CategoryRefactor.FRUITING_VEGETABLES, CategoryRefactor.POTATO,
        CategoryRefactor.SWEET_POTATO, CategoryRefactor.CUCUMBER
    );

    final List<CategoryResponse> actual = CategoryResponse.convertHierarchy(categories);

    assertThat(expected)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(actual);
  }
}
