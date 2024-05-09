package capstone.safeat.religion.dto;

import static capstone.safeat.category.domain.Category.MEATS;
import static capstone.safeat.category.domain.Category.OTHER_MOLLUSKS;
import static capstone.safeat.category.domain.Category.PORK;
import static capstone.safeat.category.domain.Category.SEA_FOODS;
import static capstone.safeat.category.dto.ReligionResponse.convertHierarchy;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Category;
import capstone.safeat.category.domain.Religion;
import capstone.safeat.category.dto.CategoryTreeResponse;
import capstone.safeat.category.dto.ReligionResponse;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ReligionResponseTest {

  @Test
  void 카테고리와_종교들을_계층형으로_변환한다() {
    final List<Religion> religions = List.of(Religion.ISLAM, Religion.HINDUISM);
    final List<ReligionResponse> expected = List.of(
        new ReligionResponse(201L, "Islam", "이슬람교", Set.of(50L, 5L, 7L, 59L),
            List.of(
                new CategoryTreeResponse(
                    MEATS.getId(), MEATS.getEnglishName(), MEATS.getKoreanName(),
                    Set.of(PORK.getId()),
                    List.of(
                        CategoryTreeResponse.fromWithEmptyChildren(PORK)
                    )
                ),
                new CategoryTreeResponse(
                    SEA_FOODS.getId(), SEA_FOODS.getEnglishName(), SEA_FOODS.getKoreanName(),
                    Set.of(OTHER_MOLLUSKS.getId()),
                    List.of(
                        CategoryTreeResponse.fromWithEmptyChildren(OTHER_MOLLUSKS)
                    )
                )
            )
        ),
        new ReligionResponse(203L, "Hinduism", "힌두교", Set.of(49L, 5L),
            List.of(
                new CategoryTreeResponse(MEATS.getId(), MEATS.getEnglishName(),
                    MEATS.getKoreanName(),
                    Set.of(49L),
                    List.of(
                        CategoryTreeResponse.fromWithEmptyChildren(Category.BEEF)
                    )
                )
            )
        )
    );

    final List<ReligionResponse> actual = convertHierarchy(religions);

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields()
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}
