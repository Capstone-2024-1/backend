package capstone.safeat.filter.external;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import capstone.safeat.filter.application.NonFoodDataFilter;
import capstone.safeat.filter.vo.Food;
import org.junit.jupiter.api.Test;

class NonFoodDataFilterTest {

  @Test
  void 한글을이_아니면_false_를_반환한다() {
    final Food validName = new Food("원");
    final Food invalidName = new Food("1,000");
    final Food invalidName2 = new Food("1,000원");
    final Food invalidName3 = new Food("-");

    assertAll(
        () -> assertThat(NonFoodDataFilter.isValidFoodName(validName))
            .isTrue(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName))
            .isFalse(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName2))
            .isFalse(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName3))
            .isFalse()
    );
  }

  @Test
  void nonFoodKeyword를_필터링한다() {
    final Food validName = new Food("제주산");
    final Food invalidName = new Food("식사류");
    final Food invalidName2 = new Food("1,000원");
    final Food invalidName3 = new Food("-");

    assertAll(
        () -> assertThat(NonFoodDataFilter.isValidFoodName(validName))
            .isFalse(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName))
            .isFalse(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName2))
            .isFalse(),
        () -> assertThat(NonFoodDataFilter.isValidFoodName(invalidName3))
            .isFalse()
    );
  }
}
