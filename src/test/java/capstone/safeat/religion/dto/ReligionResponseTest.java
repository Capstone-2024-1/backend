package capstone.safeat.religion.dto;

import static capstone.safeat.category.dto.ReligionResponse.convertHierarchy;
import static org.assertj.core.api.Assertions.assertThat;

import capstone.safeat.category.domain.Religion;
import capstone.safeat.category.dto.ReligionResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ReligionResponseTest {

  @Test
  void 카테고리와_종교들을_계층형으로_변환한다() {
    final List<Religion> religions = Arrays.stream(Religion.values()).toList();
    final List<ReligionResponse> expected = List.of(
        new ReligionResponse(201L, "Islam", "이슬람교", Set.of(50L, 59L), List.of()),
        new ReligionResponse(202L, "Judaism", "유대교",
            Set.of(65L, 50L, 66L, 67L, 68L, 69L, 70L, 71L, 59L, 60L, 61L), List.of()),
        new ReligionResponse(203L, "Hinduism", "힌두교", Set.of(49L), List.of()),
        new ReligionResponse(204L, "Buddhism", "불교", Set.of(49L, 50L, 51L, 83L, 52L, 53L),
            List.of())
    );

    final List<ReligionResponse> actual = convertHierarchy(religions);

    assertThat(actual)
        //child는 CategoryResponse에서 테스트
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("childCategories")
        .containsAnyElementsOf(expected);
  }
}
